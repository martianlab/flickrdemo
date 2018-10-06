package com.martianlab.flickrdemo.remote

import com.martianlab.flickrdemo.data.model.Photos
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import com.martianlab.flickrdemo.remote.factory.PhotoFactory
import com.martianlab.flickrdemo.remote.mapper.PhotoMapper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class FlickrRemoteImplTest {

    private lateinit var photoMapper: PhotoMapper

    private lateinit var flickrApiService: FlickrApiService

    private lateinit var flickrRemoteImpl: FlickrRemoteImpl

    @Before
    fun setup() {
        flickrApiService = mock()
        photoMapper = mock()
        flickrRemoteImpl = FlickrRemoteImpl(flickrApiService)
    }

    @Test
    fun searchPhotoCompletes() {
        stubFlickrApiServiceSearchPhotos(Single.just(PhotoFactory.makeFlickrPhotosResponse()))
        val testObserver = flickrRemoteImpl.searchPhotos(apiKey = "test", text = "test", page = 1, perpage = 100).test()
        testObserver.assertComplete()
    }

    @Test
    fun searchPhotoReturnsData() {

        val flickrSearchResponse = PhotoFactory.makeFlickrPhotosResponse()
        stubFlickrApiServiceSearchPhotos(Single.just(flickrSearchResponse))

        val flickrPhotoEntities = mutableListOf<FlickrPhoto>()
        flickrSearchResponse.photos.photo.forEach {
            flickrPhotoEntities.add(photoMapper.mapFromRemote(it))
        }

        val testObserver = flickrRemoteImpl.searchPhotos(apiKey = "test", text = "test", page = 1, perpage = 100).test()
        testObserver.assertValue(flickrSearchResponse)
    }

    private fun stubFlickrApiServiceSearchPhotos(single: Single<Photos>) {
        whenever(flickrApiService.searchPhotos(apiKey = "test", text = "test", page = 1, perpage = 100)).thenReturn(single)
    }

}