package com.martianlab.flickrdemo.domain.repository

import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import io.reactivex.Single

interface FlickrRepository {
    fun searchPhotos(apiKey:String, text:String, page:Int, perpage:Int): Single<List<FlickrPhoto>>
}