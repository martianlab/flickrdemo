package com.martianlab.flickrdemo.ui.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.martianlab.flickrdemo.data.model.Photo
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import java.util.*

class PhotoGridAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private var items: ArrayList<ViewType>
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.PHOTO, PhotoDelegateAdapter())
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun addPhotos(photos: List<FlickrPhoto>) {
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)
        items.addAll(photos)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
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