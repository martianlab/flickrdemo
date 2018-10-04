package com.martianlab.flickrdemo.ui.injection.component

import com.martianlab.flickrdemo.ui.fragment.main.MainPageFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface MainPageSubcomponent : AndroidInjector<MainPageFragment> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainPageFragment>()

}