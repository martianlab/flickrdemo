package com.martianlab.flickrdemo.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.martianlab.flickrdemo.ui.adapter.AdapterConstants
import com.martianlab.flickrdemo.ui.adapter.ViewType
import com.martianlab.flickrdemo.ui.extensions.createParcel

data class FlickrPhotoList(
    var photos: List<FlickrPhoto>,
    var text:String?,
    var page:Int = 1,
    var perpage:Int = 40
) : Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { FlickrPhotoList(it) }
    }


    protected constructor(parcelIn: Parcel) : this(
        mutableListOf<FlickrPhoto>().apply {
            parcelIn.readTypedList(this, FlickrPhoto.CREATOR)
        },
        parcelIn.readString(),
        parcelIn.readInt(),
        parcelIn.readInt()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(photos)
        dest.writeString(text)
        dest.writeInt(page)
        dest.writeInt(perpage)
    }

    override fun describeContents() = 0

}

data class FlickrPhoto(
    var id:Long,
    var owner:String,
    var secret:String,
    var server:String,
    var farm:Int,
    var title:String,
    var ispublic:Int,
    var isfriend:Int,
    var isfamily:Int,
    var url_sq:String? = null,
    var url_z:String? = null
) : ViewType, Parcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = createParcel { FlickrPhoto(it) }
    }

    protected constructor(parcelIn: Parcel) : this(
        parcelIn.readLong(),
        parcelIn.readString(),
        parcelIn.readString(),
        parcelIn.readString(),
        parcelIn.readInt(),
        parcelIn.readString(),
        parcelIn.readInt(),
        parcelIn.readInt(),
        parcelIn.readInt(),
        parcelIn.readString(),
        parcelIn.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(owner)
        dest.writeString(secret)
        dest.writeString(server)
        dest.writeInt(farm)
        dest.writeString(title)
        dest.writeInt(ispublic)
        dest.writeInt(isfriend)
        dest.writeInt(isfamily)
        dest.writeString(url_sq)
        dest.writeString(url_z)
    }

    override fun describeContents() = 0

    override fun getViewType() = AdapterConstants.PHOTO
}