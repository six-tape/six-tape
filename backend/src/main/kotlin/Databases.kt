package cl.sixtape

import io.ktor.server.application.*
import io.ktor.server.config.ApplicationConfig
import org.jetbrains.exposed.sql.*

fun Application.configureDatabases(config: ApplicationConfig) {
    val url = config.property("db.jdbcURL").getString()
    val driver = config.property("db.driver").getString()
    val user = config.property("db.user").getString()
    val password = config.property("db.password").getString()

    Database.connect(
        url,
        driver = driver,
        user = user,
        password = password
    )
}
