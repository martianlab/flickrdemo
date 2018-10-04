package com.martianlab.flickrdemo.domain.repository

import com.martianlab.flickrdemo.data.model.Photos
import io.reactivex.Single

interface FlickrRepository {
    fun searchPhotos(apiKey:String, text:String, page:Int, perpage:Int): Single<Photos>
}