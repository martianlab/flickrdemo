package com.martianlab.flickrdemo.data.model

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id") var id:Int,
    @SerializedName("owner") var owner:String,
    @SerializedName("secret") var secret:String,
    @SerializedName("server") var server:String,
    @SerializedName("farm") var farm:Int,
    @SerializedName("title") var title:String,
    @SerializedName("ispublic") var ispublic:Int,
    @SerializedName("isfriend") var isfriend:Int,
    @SerializedName("isfamily") var isfamily:Int,
    @SerializedName("url_sq") var url_sq:String
)