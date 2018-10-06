package com.martianlab.flickrdemo.ui.fragment.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martianlab.flickrdemo.R
import com.martianlab.flickrdemo.presentation.main.MainFragmentContract
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import com.martianlab.flickrdemo.domain.model.FlickrPhotoList
import com.martianlab.flickrdemo.ui.adapter.PhotoGridAdapter
import com.martianlab.flickrdemo.ui.view.InfiniteScrollListener
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_mainpage.*

class MainPageFragment: Fragment(), MainFragmentContract.View{

    @Inject lateinit var flickrListPresenter: MainFragmentContract.Presenter

    private var flickrPhotoList: FlickrPhotoList? = null

    companion object {
        private val KEY_PHOTO_LIST = "photoList"
        fun newInstance(): MainPageFragment {
            return MainPageFragment()
        }
    }

    private val flickrPhotoGrid by lazy {
        flickr_photo_list.apply {
            setHasFixedSize(true)
            //TODO: Подгонять сетку под экран
            val linearLayout = GridLayoutManager(context, 5)
            layoutManager = linearLayout

            addOnScrollListener(InfiniteScrollListener({ flickrListPresenter.loadNextPage(flickrPhotoList) }, linearLayout))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_PHOTO_LIST)) {
            flickrPhotoList = savedInstanceState.get(KEY_PHOTO_LIST) as FlickrPhotoList
            (flickrPhotoGrid.adapter as PhotoGridAdapter).clearAndAddPhotos(flickrPhotoList!!.photos)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val photos = (flickrPhotoGrid.adapter as PhotoGridAdapter).getPhotos()
        if (flickrPhotoList != null && photos.size > 0) {
            outState.putParcelable(KEY_PHOTO_LIST, flickrPhotoList?.copy(photos = photos))
        }

    }

    private fun initAdapter() {
        if (flickrPhotoGrid.adapter == null) {
            flickrPhotoGrid.adapter = PhotoGridAdapter()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mainpage, container, false)
    }

    override fun setPresenter(presenter: MainFragmentContract.Presenter) {
        flickrListPresenter = presenter
    }

    fun doSearch(text:String? = ""){
        flickrPhotoList = FlickrPhotoList( ArrayList(),text, 1, 40)
        flickrListPresenter.loadNextPage(flickrPhotoList)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun refreshMainPage(data: List<FlickrPhoto>) {
        (flickrPhotoGrid.adapter as PhotoGridAdapter).addPhotos(data)
    }

    override fun showErrorState() {
        Log.d(javaClass.simpleName, "showErrorState()")
    }

    override fun hideErrorState() {
        Log.d(javaClass.simpleName, "hideErrorState()")
    }

    override fun showEmptyState() {
        Log.d(javaClass.simpleName, "showEmptyState()")
    }

    override fun hideEmptyState() {
        Log.d(javaClass.simpleName, "hideEmptyState()")
    }

}