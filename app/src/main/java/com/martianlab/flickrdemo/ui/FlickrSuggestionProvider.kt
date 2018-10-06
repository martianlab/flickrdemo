package com.martianlab.flickrdemo.ui

import android.content.SearchRecentSuggestionsProvider

class FlickrSuggestionProvider : SearchRecentSuggestionsProvider() {
    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "com.martianlab.flickrdemo.ui.FlickrSuggestionProvider"
        const val MODE: Int = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
    }
}