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
import ru.searchassistant.database.cache.CacheDto
import ru.searchassistant.database.cache.CacheModel

fun Application.configureQueryRouting() {
    routing {
        post("/query") {

            val receive = call.receive<SearchAssistReceiveRemote>()

            val client = HttpClient(){
                install(ContentNegotiation){
                    json()
                }
            }
            val url = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/suggest/address"
            val token = "Token 85ac269ebcb5c23b3030637916d3e30abc55c11c"
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
                CacheModel.insertToDb(
                    CacheDto(
                        request = receive,
                        statusCode = call.response.status()?.value ?: 200,
                        message = call.response.status()?.description,
                        response = response
                    )
                )
            } else {
                val raw: String = apiResponse.body()
                call.respond(
                    apiResponse.status,
                    raw
                )
                CacheModel.insertToDb(
                    CacheDto(
                        request = receive,
                        statusCode = apiResponse.status.value,
                        message = apiResponse.status.description,
                        response = null
                    )
                )
            }
        }
    }
}