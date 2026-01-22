package cl.sixtape.routing

import cl.sixtape.model.movie.MovieCreation
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.moviesRouting() {
    route("/movies") {
        post {
            try {
                val movie = call.receive<MovieCreation>()
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
            val movies = repository.findAllMovies()
            call.respond(movies)
        }
        get("/byTitle/{movieTitle}") {
            val title = call.parameters["movieTitle"]
            if (title == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }
            val movie = repository.find
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
