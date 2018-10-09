package com.martianlab.flickrdemo.presentation.browse

import com.martianlab.flickrdemo.data.model.PhotoSearch
import com.martianlab.flickrdemo.domain.interactor.main.MainPageGet
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import com.martianlab.flickrdemo.domain.model.FlickrPhotoList
import com.martianlab.flickrdemo.presentation.factory.FlickrPhotoFactory
import com.martianlab.flickrdemo.presentation.main.MainFragmentContract
import com.martianlab.flickrdemo.presentation.main.MainFragmentPresenter
import com.nhaarman.mockitokotlin2.*
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainFragmentPresenterTest {

    private lateinit var mockMainFragmentView: MainFragmentContract.View
    private lateinit var mockMainPageGet: MainPageGet

    private lateinit var mainFragmentPresenter: MainFragmentPresenter
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<FlickrPhoto>>>

    @Before
    fun setup() {
        captor = argumentCaptor()
        mockMainFragmentView = mock()
        mockMainPageGet = mock()
        mainFragmentPresenter = MainFragmentPresenter(mockMainFragmentView, mockMainPageGet)
    }

    @Test
    fun loadPhotosHidesErrorState() {
        mainFragmentPresenter.loadNextPage(FlickrPhotoList(ArrayList(), "test", 0, 40))

        verify(mockMainPageGet).execute(captor.capture(), eq(PhotoSearch("test", 1, 40)))
        captor.firstValue.onSuccess(FlickrPhotoFactory.makeFlickrPhotos(10))
        verify(mockMainFragmentView).hideErrorState()
    }

    @Test
    fun loadPhotosHidesEmptyState() {
        mainFragmentPresenter.loadNextPage(FlickrPhotoList(ArrayList(), "test", 0, 40))

        verify(mockMainPageGet).execute(captor.capture(), eq(PhotoSearch("test", 1, 40)))
        captor.firstValue.onSuccess(FlickrPhotoFactory.makeFlickrPhotos(10))
        verify(mockMainFragmentView).hideEmptyState()
    }

    @Test
    fun loadPhotosShowsFlickerPhoto() {
        val flickerPhotos = FlickrPhotoFactory.makeFlickrPhotos(10)
        mainFragmentPresenter.loadNextPage(FlickrPhotoList(ArrayList(), "test", 0, 40))

        verify(mockMainPageGet).execute(captor.capture(), eq(PhotoSearch("test", 1, 40)))
        captor.firstValue.onSuccess(flickerPhotos)
        verify(mockMainFragmentView).refreshMainPage( flickerPhotos )
    }

    @Test
    fun loadPhotosShowsEmptyState() {
        mainFragmentPresenter.loadNextPage(FlickrPhotoList(ArrayList(), "test", 0, 40))

        verify(mockMainPageGet).execute(captor.capture(), eq(PhotoSearch("test", 1, 40)))
        captor.firstValue.onSuccess(FlickrPhotoFactory.makeFlickrPhotos(0))
        verify(mockMainFragmentView).showEmptyState()
    }

    @Test
    fun loadPhotosHideProgress() {
        mainFragmentPresenter.loadNextPage(FlickrPhotoList(ArrayList(), "test", 1, 40))

        verify(mockMainPageGet).execute(captor.capture(), eq(PhotoSearch("test", 2, 40)))
        captor.firstValue.onSuccess(FlickrPhotoFactory.makeFlickrPhotos(0))
        verify(mockMainFragmentView).hideProgress()
    }

    @Test
    fun loadPhotosShowsErrorState() {
        mainFragmentPresenter.loadNextPage(FlickrPhotoList(ArrayList(), "test", 0, 40))

        verify(mockMainPageGet).execute(captor.capture(), eq(PhotoSearch("test", 1, 40)))
        captor.firstValue.onError(RuntimeException())
        verify(mockMainFragmentView).showErrorState()
    }

    @Test
    fun loadPhotosBufferoosWhenErrorThrown() {
        mainFragmentPresenter.loadNextPage(FlickrPhotoList(ArrayList(), "test", 0, 40))

        verify(mockMainPageGet).execute(captor.capture(), eq(PhotoSearch("test", 1, 40)))
        captor.firstValue.onError(RuntimeException())
        verify(mockMainFragmentView).showErrorState()
    }

    @Test
    fun loadPhotosHidesEmptyStateWhenErrorThrown() {
        mainFragmentPresenter.loadNextPage(FlickrPhotoList(ArrayList(), "test", 0, 40))

        verify(mockMainPageGet).execute(captor.capture(), eq(PhotoSearch("test", 1, 40)))
        captor.firstValue.onError(RuntimeException())
        verify(mockMainFragmentView).hideEmptyState()
    }

}