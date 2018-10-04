package com.martianlab.flickrdemo.data

import com.martianlab.flickrdemo.data.model.Photos
import com.martianlab.flickrdemo.data.source.FlickrDataStoreFactory
import com.martianlab.flickrdemo.domain.repository.FlickrRepository
import io.reactivex.Single
import javax.inject.Inject

class FlickrDataRepository @Inject constructor(private val factory: FlickrDataStoreFactory) : FlickrRepository {

    //override fun clearMessages(): Completable {
    //    return factory.retrieveCacheDataStore().clearMessages()
    //}

    //override fun saveMessage(list: List<AdminMessage>): Completable {
    //    val messageEntities = list.map { messageMapper.mapToEntity(it) }
    //    return saveMessageEntities(messageEntities)
    //}

    //private fun saveAdminMessageEntities(list: List<AdminMessageListEntity>): Completable {
    //    return factory.retrieveCacheDataStore().saveAdminMessageList(list)
    //}

    override fun searchPhotos(apiKey:String, text:String, page:Int, perpage:Int): Single<Photos> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.searchPhotos(apiKey, text, page, perpage)
    }

}
