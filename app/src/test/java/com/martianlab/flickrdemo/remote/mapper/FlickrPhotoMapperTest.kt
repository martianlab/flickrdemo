package com.martianlab.flickrdemo.remote.mapper

import com.martianlab.flickrdemo.remote.factory.PhotoFactory
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PhotoMapperTest {

    private lateinit var fickrPhotoMapper: PhotoMapper

    @Before
    fun setUp() {
        fickrPhotoMapper = PhotoMapper()
    }

    @Test
    fun mapFromRemoteMapsData() {
        val flickrPhotoModel = PhotoFactory.makeFlickrPhotoModel()
        val flickrPhotoEntity = fickrPhotoMapper.mapFromRemote(flickrPhotoModel)

        assertEquals(flickrPhotoModel.id, flickrPhotoEntity.id)
        assertEquals(flickrPhotoModel.title, flickrPhotoEntity.title)
        assertEquals(flickrPhotoModel.url_sq, flickrPhotoEntity.url_sq)
    }

}