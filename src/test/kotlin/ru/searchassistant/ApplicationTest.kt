package ru.searchassistant

import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import org.jetbrains.exposed.sql.Database
import org.junit.Test
import ru.searchassistant.features.search_assist.SearchAssistReceiveRemote
import ru.searchassistant.features.search_assist.SuggestionsModel
import ru.searchassistant.features.search_assist.configureQueryRouting
import ru.searchassistant.plugins.configureRouting
import ru.searchassistant.plugins.configureSerialization
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/test").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }

    }

    @Test
    fun testProxy() = testApplication {
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        Database.connect("jdbc:postgresql://localhost:5432/search-assistant", driver = "org.postgresql.Driver",
            user = "search-assist", password = "password")
        application {
            configureSerialization()
            configureQueryRouting()
        }
        val response = client.post("/query") {
            setBody(SearchAssistReceiveRemote("автотест"))
            contentType(ContentType.Application.Json)
        }

        val responseBody: SuggestionsModel = response.body()

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Красноярский край, Емельяновский р-н, с/с Зеледеевский, тер. СНТ Автотрест", responseBody.suggestions.first().value)
    }
}
