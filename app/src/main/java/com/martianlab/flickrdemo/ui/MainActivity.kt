package com.martianlab.flickrdemo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.martianlab.flickrdemo.R
import com.martianlab.flickrdemo.ui.fragment.main.MainPageFragment
import android.app.SearchManager
import android.content.Intent
import android.annotation.SuppressLint
import android.content.Context
import android.provider.SearchRecentSuggestions
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem


class MainActivity : AppCompatActivity(){

    lateinit var mMenu:Menu
    lateinit var mainPageFragment: MainPageFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            mainPageFragment = MainPageFragment.newInstance()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.content, mainPageFragment, mainPageFragment.javaClass.simpleName)
                    .commit()
        }

        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            mainPageFragment.doSearch(query)
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                SearchRecentSuggestions(this, FlickrSuggestionProvider.AUTHORITY, FlickrSuggestionProvider.MODE).saveRecentQuery(query, null)
            }
        }
    }

    @SuppressLint("NewApi")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        mMenu = menu

        menuInflater.inflate(R.menu.main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).getActionView() as SearchView
        searchView.setSearchableInfo( searchManager.getSearchableInfo(componentName) )
        searchView.setIconifiedByDefault(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.search -> {
                onSearchRequested()
                return true
            }
            else -> return false
        }
    }

    @SuppressLint("NewApi")
    override fun onSearchRequested(): Boolean {
        val mi = mMenu.findItem(R.id.search)
        if (mi.isActionViewExpanded) {
            mi.collapseActionView()
        } else {
            mi.expandActionView()
        }

        return super.onSearchRequested()
    }
}