package com.martianlab.flickrdemo.presentation.main

import com.martianlab.flickrdemo.presentation.BasePresenter
import com.martianlab.flickrdemo.presentation.BaseView
import com.martianlab.flickrdemo.data.model.Photos

interface MainFragmentContract {

    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun refreshMainPage(data: Photos)

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }

    interface Presenter : BasePresenter {
        fun retrieveMainPage()
    }

}