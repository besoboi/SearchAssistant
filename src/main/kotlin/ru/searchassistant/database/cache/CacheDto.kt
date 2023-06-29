package ru.searchassistant.database.cache

import ru.searchassistant.features.search_assist.SearchAssistReceiveRemote
import ru.searchassistant.features.search_assist.SuggestionsModel

data class CacheDto(
    val id: Long = -1,
    val request: SearchAssistReceiveRemote,
    val statusCode: Int,
    val message: String?,
    val response: SuggestionsModel?
)