package com.martianlab.flickrdemo.ui.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.martianlab.flickrdemo.data.model.Photo
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import com.martianlab.flickrdemo.ui.fragment.main.OnPhotoClickListener
import java.util.*

class PhotoGridAdapter(
    private var listener:OnPhotoClickListener

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private var items: ArrayList<ViewType>

    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.PHOTO, PhotoDelegateAdapter())
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        items = ArrayList()
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, this.items[position], listener)
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun clear(){
        items.clear()
        notifyDataSetChanged()
    }

    fun addPhotos(photos: List<FlickrPhoto>) {
        var initPosition = items.size - 1
        if( items.size > 0 ) {
            items.removeAt(initPosition)
            notifyItemRemoved(initPosition)
        } else {
            initPosition = 0
        }
        items.addAll(photos)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 )
    }

    fun clearAndAddPhotos(photos: List<FlickrPhoto>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(photos)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getPhotos(): List<FlickrPhoto> {
        return items
                .filter { it.getViewType() == AdapterConstants.PHOTO }
                .map { it as FlickrPhoto }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}