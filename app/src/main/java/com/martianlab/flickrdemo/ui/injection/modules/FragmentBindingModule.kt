package com.martianlab.flickrdemo.ui.injection.modules

import com.martianlab.flickrdemo.ui.fragment.main.MainPageFragment
import com.martianlab.flickrdemo.ui.injection.scopes.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(MainFragmentModule::class))
    abstract fun bindMainPageFragment(): MainPageFragment

}