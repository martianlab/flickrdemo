package com.martianlab.flickrdemo.data.source

import com.martianlab.flickrdemo.data.repository.FlickrDataStore
import javax.inject.Inject

open class FlickrDataStoreFactory @Inject constructor( private val flickrRemoteDataStore: FlickrRemoteDataStore ) {

    open fun retrieveDataStore(): FlickrDataStore {
        //если вдруг мы надумаем написать кэш
        //if (flickrCache.isCached() && !flickrCache.isExpired()) {
        //    return retrieveCacheDataStore()
        //}
        return retrieveRemoteDataStore()
    }

    open fun retrieveRemoteDataStore(): FlickrDataStore {
        return flickrRemoteDataStore
    }

}