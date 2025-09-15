package cl.sixtape

import cl.sixtape.model.movie.Movie
import cl.sixtape.model.movie.PostgresMovieRepository
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
    @Test
    fun newMoviesCanBeAdded() = testApplication {
        application {
            val repository = PostgresMovieRepository()
            configureSerialization(repository)
            configureRouting()
            configureDatabases()
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        val movie = Movie(
            "Superman",
            2025,
            130,
            "Superman, a journalist in Metropolis, embarks on a journey to reconcile his Kryptonian heritage with his human upbringing as Clark Kent."
        )
        val responsePost = client.post("/movies") {
            header(HttpHeaders.ContentType, ContentType.Application.Json)

            setBody(movie)
        }

        assertEquals(HttpStatusCode.NoContent, responsePost.status)

        val responseGet = client.get("/movies")
        assertEquals(HttpStatusCode.OK, responseGet.status)

        val movieTitles = responseGet.body<List<Movie>>().map { it.title }
        assertContains(movieTitles, "Superman")
    }

}
