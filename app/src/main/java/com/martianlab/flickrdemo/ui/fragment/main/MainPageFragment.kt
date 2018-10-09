package com.martianlab.flickrdemo.ui.fragment.main

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
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

class MainPageFragment: Fragment(), MainFragmentContract.View, OnPhotoClickListener{

    @Inject lateinit var flickrListPresenter: MainFragmentContract.Presenter

    private var flickrPhotoList: FlickrPhotoList? = null
    private var query:String? = null

    companion object {
        private val KEY_PHOTO_LIST = "photoList"
        fun newInstance(query:String = ""): MainPageFragment {
            var f = MainPageFragment()
            f.query = query
            return f
        }
    }

    private val flickrPhotoGrid by lazy {
        flickr_photo_list.apply {
            //setHasFixedSize(true)
            //TODO: Подгонять сетку под экран
            // можно брать значения из ресурсов <integer
            // можно считать через displaymetrics
            val layout = GridLayoutManager(context, resources.getInteger(R.integer.grid_num_columns))
            layoutManager = layout

            addOnScrollListener(InfiniteScrollListener({ flickrListPresenter.loadNextPage(flickrPhotoList) }, layout))
        }
    }

    override fun onStart(){
        super.onStart()
        if( !TextUtils.isEmpty(query?:"") ){
            doSearch(query)
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
            flickrPhotoGrid.adapter = PhotoGridAdapter(this)
        }
    }

    override fun OnPhotoClick(urlPhoto: String) {
        val bigPhotoFragment = BigPhotoFragment.newInstance(urlPhoto)
        bigPhotoFragment.show( childFragmentManager, bigPhotoFragment.javaClass.simpleName )
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mainpage, container, false)
    }

    override fun setPresenter(presenter: MainFragmentContract.Presenter) {
        flickrListPresenter = presenter
    }

    fun doSearch(text:String? = ""){
        query = text?:""
        (flickrPhotoGrid.adapter as PhotoGridAdapter).clear()
        flickrPhotoList = FlickrPhotoList( ArrayList(),query, 0, 40)
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