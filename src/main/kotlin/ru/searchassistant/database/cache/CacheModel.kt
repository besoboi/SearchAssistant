package ru.searchassistant.database.cache

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.searchassistant.features.search_assist.SearchAssistReceiveRemote
import ru.searchassistant.features.search_assist.SuggestionsModel

object CacheModel : Table("cache_table") {
    private val request = CacheModel.json<SearchAssistReceiveRemote>(
        "request",
        { Json.encodeToString(it as SearchAssistReceiveRemote) },
        { Json.decodeFromString(it) as SearchAssistReceiveRemote }
    )
    private val response = CacheModel.json<SuggestionsModel>(
        "response",
        { Json.encodeToString(it as SuggestionsModel) },
        { Json.decodeFromString(it) as SuggestionsModel }
    )

    fun insertToDb(cacheDto: CacheDto) {
        transaction {
            CacheModel.insert {
                it[request] = cacheDto.request
                it[response] = cacheDto.response
            }
        }
    }

    fun fetchRecord(request: SearchAssistReceiveRemote): CacheDto? {
        return try {
            val cacheModel = transaction { CacheModel.select { CacheModel.request.eq(request) }.single() }
            return CacheDto(
                request = cacheModel[CacheModel.request],
                response = cacheModel[response]
            )
        } catch (e: NoSuchElementException) {
            null
        }
    }


}