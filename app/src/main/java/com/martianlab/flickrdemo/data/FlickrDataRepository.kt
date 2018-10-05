package com.martianlab.flickrdemo.data

import com.martianlab.flickrdemo.data.source.FlickrDataStoreFactory
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import com.martianlab.flickrdemo.domain.repository.FlickrRepository
import io.reactivex.Single
import javax.inject.Inject

class FlickrDataRepository @Inject constructor(
      private val factory: FlickrDataStoreFactory
) : FlickrRepository {

    override fun searchPhotos(apiKey:String, text:String, page:Int, perpage:Int): Single<List<FlickrPhoto>> {
        val dataStore = factory.retrieveDataStore()
        return dataStore.searchPhotos(apiKey, text, page, perpage)
    }

}
