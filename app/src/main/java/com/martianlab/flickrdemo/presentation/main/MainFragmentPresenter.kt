package com.martianlab.flickrdemo.presentation.main

import com.martianlab.flickrdemo.data.model.PhotoSearch
import com.martianlab.flickrdemo.domain.interactor.SingleUseCase
import com.martianlab.flickrdemo.data.model.Photos
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MainFragmentPresenter @Inject constructor(val mainPageFragment: MainFragmentContract.View,
                                                val getMainPageUseCase: SingleUseCase<Photos, PhotoSearch>
):
        MainFragmentContract.Presenter {

    init {
        mainPageFragment.setPresenter(this)
    }

    override fun start() {
        retrieveMainPage()
    }

    override fun stop() {
        getMainPageUseCase.dispose()
    }

    override fun retrieveMainPage() {
        getMainPageUseCase.execute(MainpageGetSubscriber(), PhotoSearch("текст", 1, 40))
    }

    internal fun handleGetMainPageSuccess(data: Photos) {
        mainPageFragment.hideErrorState()
        mainPageFragment.hideEmptyState()
        mainPageFragment.refreshMainPage( data )
    }

    inner class MainpageGetSubscriber: DisposableSingleObserver<Photos>() {

        override fun onSuccess(t: Photos) {
            handleGetMainPageSuccess(t)
        }

        override fun onError(exception: Throwable) {
            mainPageFragment.hideEmptyState()
            mainPageFragment.showErrorState()
        }

    }

}