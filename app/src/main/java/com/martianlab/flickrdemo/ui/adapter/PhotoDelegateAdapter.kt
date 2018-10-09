package com.martianlab.flickrdemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.martianlab.flickrdemo.R
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import com.martianlab.flickrdemo.ui.extensions.inflate
import com.martianlab.flickrdemo.ui.extensions.loadImg
import com.martianlab.flickrdemo.ui.fragment.main.OnPhotoClickListener
import kotlinx.android.synthetic.main.photo_item.view.*

class PhotoDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PhotoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, listener:OnPhotoClickListener) {
        holder as PhotoViewHolder
        holder.bind(item as FlickrPhoto, listener)
    }

    class PhotoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.photo_item)) {

        fun bind(item: FlickrPhoto, listener:OnPhotoClickListener) = with(itemView) {
            flickr_photo.loadImg(item.url_sq?:"")
            flickr_photo.setOnClickListener { listener.OnPhotoClick(item.url_z?:"") }
        }
    }
}