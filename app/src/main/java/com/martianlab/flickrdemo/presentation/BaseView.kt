package com.martianlab.flickrdemo.presentation

interface BaseView<in T : BasePresenter> {

    fun setPresenter(presenter: T)

}