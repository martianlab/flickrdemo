package com.martianlab.flickrdemo.remote.mapper

import com.martianlab.flickrdemo.data.model.Photo
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import javax.inject.Inject

open class PhotoMapper @Inject constructor(): EntityMapper<Photo, FlickrPhoto> {

    override fun mapFromRemote(type: Photo): FlickrPhoto {
        return FlickrPhoto(type.id, type.owner, type.secret, type.server, type.farm, type.title, type.ispublic, type.isfriend, type.isfamily, type.url_sq )
    }

}