package com.martianlab.flickrdemo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.martianlab.flickrdemo.R
import com.martianlab.flickrdemo.ui.fragment.main.MainPageFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.content, MainPageFragment.newInstance(), MainPageFragment.javaClass.simpleName)
                    .commit()
        }
    }
}