package com.martianlab.flickrdemo.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.martianlab.flickrdemo.R
import com.martianlab.flickrdemo.ui.fragment.main.MainPageFragment
import android.app.SearchManager
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.provider.SearchRecentSuggestions
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem




class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, SearchView.OnSuggestionListener{

    lateinit var mMenu:Menu
    lateinit var mainPageFragment: MainPageFragment
    lateinit var mSearchView:SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            var queryStr = ""
            if (Intent.ACTION_SEARCH == intent.action) {
                intent.getStringExtra(SearchManager.QUERY)?.also { query -> queryStr = query }
            }
            mainPageFragment = MainPageFragment.newInstance(queryStr)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.content, mainPageFragment, mainPageFragment.javaClass.simpleName)
                    .commit()
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        SearchRecentSuggestions(this, FlickrSuggestionProvider.AUTHORITY, FlickrSuggestionProvider.MODE).saveRecentQuery(query, null)
        mainPageFragment.doSearch(query?:"")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onSuggestionSelect(position: Int): Boolean {
        return true
    }

    override fun onSuggestionClick(position: Int): Boolean {
        val cursor = mSearchView.getSuggestionsAdapter().getCursor()
        cursor.moveToPosition(position)
        val query = cursor.getString(2)
        Log.d(javaClass.simpleName, query)
        mSearchView.setQuery(query, true)
        mainPageFragment.doSearch( query )
        return true
    }


    @SuppressLint("NewApi")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        mMenu = menu

        menuInflater.inflate(R.menu.main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        mSearchView = menu.findItem(R.id.search).getActionView() as SearchView
        mSearchView.setOnQueryTextListener(this)
        mSearchView.setOnSuggestionListener(this)
        mSearchView.setSearchableInfo( searchManager.getSearchableInfo(componentName) )
        mSearchView.setIconifiedByDefault(false)
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