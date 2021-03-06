package com.martianlab.flickrdemo.data.repository

import com.martianlab.flickrdemo.data.model.Photos
import io.reactivex.Single

interface FlickrRemote {
    fun searchPhotos(apiKey: String, text:String, page:Int, perpage:Int): Single<Photos>
}