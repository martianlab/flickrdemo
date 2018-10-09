package com.martianlab.flickrdemo.presentation.factory

import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import com.martianlab.flickrdemo.remote.factory.RandomDataFactory.Factory.randomImageUrl
import com.martianlab.flickrdemo.remote.factory.RandomDataFactory.Factory.randomInt
import com.martianlab.flickrdemo.remote.factory.RandomDataFactory.Factory.randomLong
import com.martianlab.flickrdemo.remote.factory.RandomDataFactory.Factory.randomUuid

class FlickrPhotoFactory {

    companion object Factory {

        fun makeFlickrPhotos( count: Int ) : List<FlickrPhoto> {
            val flickrPhotosList = mutableListOf<FlickrPhoto>()
            repeat(count) {
                flickrPhotosList.add( makeFlickrPhotoModel() )
            }
            return flickrPhotosList
        }

        fun makeFlickrPhotoModel(): FlickrPhoto {
            return FlickrPhoto(
                      id = randomLong()
                    , owner = randomUuid()
                    , secret = randomUuid()
                    , server = randomUuid()
                    , farm = randomInt()
                    , title = randomUuid()
                    , ispublic = randomInt()
                    , isfriend =  randomInt()
                    , isfamily = randomInt()
                    , url_sq = randomImageUrl()
            )
        }

    }

}