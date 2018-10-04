package com.martianlab.flickrdemo.ui.injection.modules

import com.martianlab.flickrdemo.domain.interactor.main.MainPageGet
import com.martianlab.flickrdemo.presentation.main.MainFragmentContract
import com.martianlab.flickrdemo.presentation.main.MainFragmentPresenter
import com.martianlab.flickrdemo.ui.fragment.main.MainPageFragment
import com.martianlab.flickrdemo.ui.injection.scopes.PerFragment
import dagger.Module
import dagger.Provides

@Module
open class MainFragmentModule {

    @PerFragment
    @Provides
    internal fun provideMainPageView(mainPageFragment: MainPageFragment): MainFragmentContract.View {
        return mainPageFragment
    }

    @PerFragment
    @Provides
    internal fun provideMainFragmentPresenter(mainView: MainFragmentContract.View, getMainPage: MainPageGet): MainFragmentContract.Presenter {
        return MainFragmentPresenter(mainView, getMainPage)
    }

}