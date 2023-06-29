package ru.searchassistant

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.searchassistant.features.search_assist.configureQueryRouting
import ru.searchassistant.plugins.configureRouting
import ru.searchassistant.plugins.configureSerialization

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/search-assistant", driver = "org.postgresql.Driver",
        user = "search-assist", password = "password")

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureQueryRouting()
    configureRouting()
}
