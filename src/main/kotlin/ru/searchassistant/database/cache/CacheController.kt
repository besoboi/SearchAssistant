package ru.searchassistant.database.cache

import ru.searchassistant.features.search_assist.SearchAssistReceiveRemote

class CacheController {
    fun getRecordIfExist(request: SearchAssistReceiveRemote): CacheDto? {
        return CacheModel.fetchRecord(request)
    }

    fun insertRecord(cacheDto: CacheDto) {
        CacheModel.insertToDb(cacheDto)
    }
}