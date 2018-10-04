package com.martianlab.flickrdemo.ui.fragment.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martianlab.flickrdemo.R
import com.martianlab.flickrdemo.presentation.main.MainFragmentContract
import com.martianlab.flickrdemo.data.model.Photos
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainPageFragment: Fragment(), MainFragmentContract.View{

    @Inject lateinit var onboardingPresenter: MainFragmentContract.Presenter

    companion object {
        fun newInstance(): MainPageFragment {
            return MainPageFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mainpage, container, false)
    }

    override fun setPresenter(presenter: MainFragmentContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun refreshMainPage(data: Photos) {
    }

    override fun showErrorState() {
    }

    override fun hideErrorState() {
    }

    override fun showEmptyState() {
    }

    override fun hideEmptyState() {
    }

}