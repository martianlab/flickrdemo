package com.martianlab.flickrdemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.martianlab.flickrdemo.R
import com.martianlab.flickrdemo.data.model.Photo
import com.martianlab.flickrdemo.domain.model.FlickrPhoto
import com.martianlab.flickrdemo.ui.extensions.inflate
import com.martianlab.flickrdemo.ui.extensions.loadImg
import kotlinx.android.synthetic.main.photo_item.view.*

class PhotoDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PhotoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as PhotoViewHolder
        holder.bind(item as FlickrPhoto)
    }

    class PhotoViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.photo_item)) {

        fun bind(item: FlickrPhoto) = with(itemView) {
            img_thumbnail.loadImg(item.url_sq)
            description.text = item.title
            author.text = item.title
        }
    }
}