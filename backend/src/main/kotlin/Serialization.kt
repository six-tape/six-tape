package cl.sixtape

import cl.sixtape.model.Movie
import cl.sixtape.model.MovieRepository
import io.ktor.http.*
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization(repository: MovieRepository) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        route("/movies") {
            post {
                try {
                    val movie = call.receive<Movie>()
                    repository.addMovie(movie)
                    call.respond(HttpStatusCode.NoContent)
                } catch (e: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (e: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }

            get {
                val movies = repository.allMovies()
                call.respond(movies)
            }

            get("/byTitle/{movieTitle}") {
                val title = call.parameters["movieTitle"]
                if (title == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val movie = repository.movieByTitle(title)
                if (movie == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(movie)
            }

            delete("/{movieTitle}") {
                val movieTitle = call.parameters["movieTitle"]
                if (movieTitle == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                if (repository.removeMovie(movieTitle)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}
