package com.martianlab.flickrdemo.data.source

import com.martianlab.flickrdemo.data.mapper.FlickrPhotoMapper
import com.martianlab.flickrdemo.data.model.Photos
import com.martianlab.flickrdemo.data.repository.FlickrDataStore
import com.martianlab.flickrdemo.data.repository.FlickrRemote
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import io.reactivex.Single
import javax.inject.Inject

open class FlickrRemoteDataStore @Inject constructor(private val flickrRemote: FlickrRemote, private val flickrPhotoMapper: FlickrPhotoMapper) : FlickrDataStore {

    override fun searchPhotos(apiKey: String, text: String, page: Int, perpage: Int): Single<List<FlickrPhoto>> {
        return flickrRemote.searchPhotos(apiKey, text, page, perpage)
                .map{ list -> list.photos }
                .map { list -> list.photo.map { item -> flickrPhotoMapper.mapFromEntity(item) } }
    }

}