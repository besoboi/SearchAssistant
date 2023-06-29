package ru.searchassistant.features.search_assist

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.searchassistant.database.cache.CacheController
import ru.searchassistant.database.cache.CacheDto

fun Application.configureQueryRouting() {
    routing {
        post("/query") {
            val url = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/address"
            val token = "Token 85ac269ebcb5c23b3030637916d3e30abc55c11c"
            val receive = call.receive<SearchAssistReceiveRemote>()

            val cacheController = CacheController()
            val cacheDto = cacheController.getRecordIfExist(receive)
            if (cacheDto != null) {
                call.respond(
                    cacheDto.response
                )
            } else {
                val client = HttpClient() {
                    install(ContentNegotiation) {
                        json()
                    }
                }

                val apiResponse = client.post(url) {
                    setBody(receive)
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                    bearerAuth(token)
                }
                client.close()
                if (apiResponse.status.value == 200) {
                    val response: SuggestionsModel = apiResponse.body()
                    call.respond(
                        response
                    )
                    cacheController.insertRecord(
                        CacheDto(
                            request = receive,
                            response = response
                        )
                    )
                } else {
                    val raw: String = apiResponse.body()
                    call.respond(
                        apiResponse.status,
                        raw
                    )
                }
            }
        }
    }
}