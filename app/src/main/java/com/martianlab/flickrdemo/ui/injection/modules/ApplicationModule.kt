package com.martianlab.flickrdemo.ui.injection.modules

import android.app.Application
import android.content.Context
import com.martianlab.flickrdemo.BuildConfig
import com.martianlab.flickrdemo.data.FlickrDataRepository
import com.martianlab.flickrdemo.data.executor.JobExecutor
import com.martianlab.flickrdemo.data.repository.FlickrRemote
import com.martianlab.flickrdemo.data.source.FlickrDataStoreFactory
import com.martianlab.flickrdemo.domain.executor.PostExecutionThread
import com.martianlab.flickrdemo.domain.executor.ThreadExecutor
import com.martianlab.flickrdemo.domain.repository.FlickrRepository
import com.martianlab.flickrdemo.remote.FlickrApiService
import com.martianlab.flickrdemo.remote.FlickrRemoteImpl
import com.martianlab.flickrdemo.remote.FlickrServiceFactory
import com.martianlab.flickrdemo.ui.UiThread
import com.martianlab.flickrdemo.ui.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides


@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun provideFlickrRepository(factory : FlickrDataStoreFactory): FlickrRepository {
        return FlickrDataRepository(factory)
    }

    @Provides
    @PerApplication
    internal fun provideFlickrRemote(service: FlickrApiService): FlickrRemote {
        return FlickrRemoteImpl(service)
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideFlickrService(): FlickrApiService {
        return FlickrServiceFactory.makeFlickrService(BuildConfig.DEBUG)
    }
}