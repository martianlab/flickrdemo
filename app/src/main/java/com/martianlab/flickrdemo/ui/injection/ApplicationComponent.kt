package com.martianlab.flickrdemo.ui.injection

import android.app.Application
import com.martianlab.flickrdemo.ui.FlickrDemoApplication
import com.martianlab.flickrdemo.ui.injection.modules.ApplicationModule
import com.martianlab.flickrdemo.ui.injection.modules.FragmentBindingModule
import com.martianlab.flickrdemo.ui.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = arrayOf(FragmentBindingModule::class, ApplicationModule::class, AndroidSupportInjectionModule::class))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: FlickrDemoApplication)

}