package com.martianlab.flickrdemo.ui

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.martianlab.flickrdemo.ui.injection.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class FlickrDemoApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return mActivityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentInjector
    }

}