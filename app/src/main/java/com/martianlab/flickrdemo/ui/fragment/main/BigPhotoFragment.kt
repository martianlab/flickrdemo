package com.martianlab.flickrdemo.ui.fragment.main

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.martianlab.flickrdemo.R
import com.martianlab.flickrdemo.ui.extensions.loadImg
import kotlinx.android.synthetic.main.fragment_photo_big.*


class BigPhotoFragment: DialogFragment(){

    lateinit var url:String

    companion object {
        fun newInstance(url:String): BigPhotoFragment {
            var f = BigPhotoFragment()
            f.url = url
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_big, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photo.loadImg(url)
    }

}