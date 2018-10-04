package com.martianlab.flickrdemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.martianlab.flickrdemo.R
import com.martianlab.flickrdemo.data.model.Photo
import com.martianlab.flickrdemo.ui.extensions.inflate
import com.martianlab.flickrdemo.ui.extensions.loadImg
import kotlinx.android.synthetic.main.photo_item.view.*

class SimpleNewsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as Photo)
    }

    class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.photo_item)) {

        fun bind(item: Photo) = with(itemView) {
            //Picasso.with(itemView.context).load(item.thumbnail).into(img_thumbnail)
            img_thumbnail.loadImg(item.url_sq)
            description.text = item.title
            author.text = item.title
        }
    }
}