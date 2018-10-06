package com.martianlab.flickrdemo.remote.factory

import com.martianlab.flickrdemo.data.model.Photo
import com.martianlab.flickrdemo.data.model.Photos
import com.martianlab.flickrdemo.data.model.PhotosList
import com.martianlab.flickrdemo.remote.factory.RandomDataFactory.Factory.randomImageUrl
import com.martianlab.flickrdemo.remote.factory.RandomDataFactory.Factory.randomInt
import com.martianlab.flickrdemo.remote.factory.RandomDataFactory.Factory.randomLong
import com.martianlab.flickrdemo.remote.factory.RandomDataFactory.Factory.randomUuid

class PhotoFactory {

    companion object Factory {

        fun makeFlickrPhotosResponse(): Photos {
            return Photos(makeFlickrPhotoList(20))
        }

        fun makeFlickrPhotoList(count: Int): PhotosList {
            return PhotosList(page = 1, pages = 10, perpage = 40, total = 400, photo = makeFlickrPhotos(count))
        }

        fun makeFlickrPhotos( count: Int ) : List<Photo> {
            val flickrPhotosList = mutableListOf<Photo>()
            repeat(count) {
                flickrPhotosList.add( makeFlickrPhotoModel() )
            }
            return flickrPhotosList
        }

        fun makeFlickrPhotoModel(): Photo {
            return Photo(
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