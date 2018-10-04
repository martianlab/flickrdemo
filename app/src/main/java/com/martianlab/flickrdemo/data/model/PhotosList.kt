package com.martianlab.flickrdemo.data.model

import com.google.gson.annotations.SerializedName

data class PhotosList (
    @SerializedName("page") var page: Int,
    @SerializedName("pages") var pages: Int,
    @SerializedName("perpage")  var perpage: Int,
    @SerializedName("total") var total: Int,
    @SerializedName("photo") var photo: List<Photo>
)