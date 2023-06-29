package ru.searchassistant.features.search_assist

import kotlinx.serialization.Serializable

@Serializable
data class ValueModel(
    val value: String? = null,
    val unrestricted_value: String? = null,
    val data: SearchAssistResponseRemote
)
