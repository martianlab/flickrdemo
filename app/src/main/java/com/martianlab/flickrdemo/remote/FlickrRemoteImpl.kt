package com.martianlab.flickrdemo.remote

import com.martianlab.flickrdemo.data.model.Photos
import com.martianlab.flickrdemo.data.repository.FlickrRemote
import io.reactivex.Single
import javax.inject.Inject

class FlickrRemoteImpl @Inject constructor(private val flickrService: FlickrApiService) : FlickrRemote {

    override fun searchPhotos(apiKey: String, text: String, page: Int, perpage: Int): Single<Photos> {
        return flickrService.searchPhotos(apiKey, text, page, perpage)
    }

}