package com.martianlab.flickrdemo.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.martianlab.flickrdemo.ui.fragment.main.OnPhotoClickListener

interface ViewTypeDelegateAdapter {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType, listener:OnPhotoClickListener)
}