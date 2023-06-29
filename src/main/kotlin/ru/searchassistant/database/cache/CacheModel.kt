package ru.searchassistant.database.cache

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import ru.searchassistant.features.search_assist.SearchAssistReceiveRemote
import ru.searchassistant.features.search_assist.SuggestionsModel

object CacheModel : IntIdTable("cache", "id") {
    private val request = CacheModel.json<SearchAssistReceiveRemote>(
        "request",
        { Json.encodeToString(it as SearchAssistReceiveRemote) },
        { Json.decodeFromString(it) as SearchAssistReceiveRemote }
    )
    private val statusCode = CacheModel.integer("status_code")
    private val message = CacheModel.varchar("message", 50).nullable()
    private val response = CacheModel.json<SuggestionsModel>(
        "response",
        { Json.encodeToString(it as SuggestionsModel) },
        { Json.decodeFromString(it) as SuggestionsModel }
    ).nullable()

    fun insertToDb(cacheDto: CacheDto) {
        transaction {
            CacheModel.insert {
                it[request] = cacheDto.request
                it[statusCode] = cacheDto.statusCode
                it[message] = cacheDto.message
                it[response] = cacheDto.response
            }
        }
    }
}





