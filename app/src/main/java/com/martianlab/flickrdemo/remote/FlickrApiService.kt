package com.martianlab.flickrdemo.remote

import com.martianlab.flickrdemo.FLICKR_FORMAT_JSON
import com.martianlab.flickrdemo.FLICKR_JSON_CALLBACK
import com.martianlab.flickrdemo.FLICKR_URL_PREFIX
import com.martianlab.flickrdemo.data.model.Photos
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal const val EXTRAS = "url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o"

interface FlickrApiService {
    /**
     * https://www.flickr.com/services/api/flickr.photos.search.html
     */
    @GET(FLICKR_URL_PREFIX +"flickr.photos.search" + FLICKR_JSON_CALLBACK + FLICKR_FORMAT_JSON)
    fun searchPhotos(
        @Query("api_key") apiKey: String,             // req
        @Query("text") text: String? = null,          // opt A free text search. Photos who's title, description or tags contain the text will be returned. You can exclude results that match a term by prepending it with a - character
        @Query("page") page: Int? = 1,             // The page of results to return
        @Query("perpage") perpage: Int? = 30,         // Number of photos to return per page
        @Query("extras") extras: String = EXTRAS      // opt extra information to fetch for each returned record
    ): Single<Photos>

}