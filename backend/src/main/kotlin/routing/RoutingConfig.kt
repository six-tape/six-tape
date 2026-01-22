package cl.sixtape.routing

import cl.sixtape.model.movie.MovieCreation
import cl.sixtape.repository.MovieRepository
import io.ktor.http.*
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(repository: MovieRepository) {
    install(ContentNegotiation) {
        json()
    }
    //TODO: Add specific exceptions to status pages
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        moviesRouting()

        get("/healthcheck") {
            call.respondText("Healthy")
        }
    }
}
