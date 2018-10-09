package com.martianlab.flickrdemo.data.mapper

import com.martianlab.flickrdemo.data.model.Photo
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import javax.inject.Inject


open class FlickrPhotoMapper @Inject constructor(): Mapper<Photo, FlickrPhoto> {

    override fun mapFromEntity(type: Photo): FlickrPhoto {
        return FlickrPhoto(type.id, type.owner, type.secret, type.server, type.farm, type.title, type.ispublic, type.isfriend, type.isfamily, type.url_sq, type.url_z )
    }

    override fun mapToEntity(type: FlickrPhoto): Photo {
        return Photo(type.id, type.owner, type.secret, type.server, type.farm, type.title, type.ispublic, type.isfriend, type.isfamily, type.url_sq, type.url_z)
    }


}