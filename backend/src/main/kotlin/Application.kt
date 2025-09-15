package cl.sixtape

import cl.sixtape.model.movie.PostgresMovieRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val repository = PostgresMovieRepository()

    configureSerialization(repository)
    configureDatabases()
    configureRouting()
}
