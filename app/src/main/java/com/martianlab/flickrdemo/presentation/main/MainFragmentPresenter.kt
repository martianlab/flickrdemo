package com.martianlab.flickrdemo.presentation.main

import android.util.Log
import com.martianlab.flickrdemo.data.model.PhotoSearch
import com.martianlab.flickrdemo.domain.interactor.SingleUseCase
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import com.martianlab.flickrdemo.domain.model.FlickrPhotoList
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MainFragmentPresenter @Inject constructor(val mainPageFragment: MainFragmentContract.View,
                                                val getMainPageUseCase: SingleUseCase<List<FlickrPhoto>, PhotoSearch?>
):
        MainFragmentContract.Presenter {

    init {
        mainPageFragment.setPresenter(this)
    }

    override fun start() {
        //newSearch("")
    }

    override fun stop() {
        getMainPageUseCase.dispose()
    }

    override fun loadNextPage(flickrPhotoList: FlickrPhotoList?) {
        if( flickrPhotoList != null ) {
            flickrPhotoList.page++
            getMainPageUseCase.execute(MainpageGetSubscriber(), PhotoSearch(flickrPhotoList.text
                    ?: "", flickrPhotoList.page, flickrPhotoList.perpage))
        }
   }

    internal fun handleGetMainPageSuccess(data: List<FlickrPhoto>) {
        mainPageFragment.hideErrorState()
        mainPageFragment.hideEmptyState()
        mainPageFragment.refreshMainPage( data )
    }

    inner class MainpageGetSubscriber: DisposableSingleObserver<List<FlickrPhoto>>() {

        override fun onSuccess(t: List<FlickrPhoto>) {
            handleGetMainPageSuccess(t)
        }

        override fun onError(exception: Throwable) {
            Log.e(javaClass.simpleName, exception.message)
            mainPageFragment.hideEmptyState()
            mainPageFragment.showErrorState()
        }

    }

}