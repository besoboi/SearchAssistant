package ru.searchassistant.features.search_assist

import kotlinx.serialization.Serializable

@Serializable
data class SuggestionsModel(
    val suggestions: ArrayList<ValueModel>
)
