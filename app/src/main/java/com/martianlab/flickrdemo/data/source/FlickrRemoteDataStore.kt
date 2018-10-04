package com.martianlab.flickrdemo.data.source

import com.martianlab.flickrdemo.data.model.Photos
import com.martianlab.flickrdemo.data.repository.FlickrDataStore
import com.martianlab.flickrdemo.data.repository.FlickrRemote
import io.reactivex.Single
import javax.inject.Inject

open class FlickrRemoteDataStore @Inject constructor(private val flickrRemote: FlickrRemote) : FlickrDataStore {

    override fun searchPhotos(apiKey: String, text: String, page: Int, perpage: Int): Single<Photos> {
        return flickrRemote.searchPhotos(apiKey, text, page, perpage)
    }

}