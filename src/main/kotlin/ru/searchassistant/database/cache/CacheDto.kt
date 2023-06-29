package ru.searchassistant.database.cache

import ru.searchassistant.features.search_assist.SearchAssistReceiveRemote
import ru.searchassistant.features.search_assist.SuggestionsModel

data class CacheDto(
    val request: SearchAssistReceiveRemote,
    val response: SuggestionsModel
)