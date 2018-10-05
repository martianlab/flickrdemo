package com.martianlab.flickrdemo.presentation.main

import com.martianlab.flickrdemo.presentation.BasePresenter
import com.martianlab.flickrdemo.presentation.BaseView
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import com.martianlab.flickrdemo.domain.model.FlickrPhotoList

interface MainFragmentContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun refreshMainPage(data: List<FlickrPhoto>)

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {
        fun loadNextPage(flickrPhotoList: FlickrPhotoList?)
    }

}