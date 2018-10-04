package com.martianlab.flickrdemo.data.model

import com.google.gson.annotations.SerializedName

data class Photos (
    @SerializedName("Photos") var photos: PhotosList
)