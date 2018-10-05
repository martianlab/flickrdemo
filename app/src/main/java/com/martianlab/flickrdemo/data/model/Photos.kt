package com.martianlab.flickrdemo.data.model

import com.google.gson.annotations.SerializedName

data class Photos (
    @SerializedName("photos") var photos: PhotosList
)