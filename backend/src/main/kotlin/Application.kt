package cl.sixtape

import cl.sixtape.repository.PostgresMovieRepository
import cl.sixtape.routing.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val repository = PostgresMovieRepository()

    configureDatabases(environment.config)
    configureRouting(repository)
}
