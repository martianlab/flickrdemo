package com.martianlab.flickrdemo.domain.interactor.main

import com.martianlab.flickrdemo.FLICKR_API_KEY
import com.martianlab.flickrdemo.data.model.PhotoSearch
import com.martianlab.flickrdemo.domain.executor.PostExecutionThread
import com.martianlab.flickrdemo.domain.executor.ThreadExecutor
import com.martianlab.flickrdemo.domain.interactor.SingleUseCase
import com.martianlab.flickrdemo.data.model.Photos
import com.martianlab.flickrdemo.data.source.FlickrRemoteDataStore
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import io.reactivex.Single
import javax.inject.Inject

open class MainPageGet @Inject constructor(val flickrRepository: FlickrRemoteDataStore, threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread)
    :  SingleUseCase<List<FlickrPhoto>, PhotoSearch?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: PhotoSearch?): Single<List<FlickrPhoto>> {
        return flickrRepository.searchPhotos(FLICKR_API_KEY, params?.text?:"", params?.page?:1, params?.perpage?:30)
    }

}