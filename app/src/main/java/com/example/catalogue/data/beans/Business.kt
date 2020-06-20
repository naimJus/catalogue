package com.example.catalogue.data.beans

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Business(
    @SerializedName("id") val id: String,
    @SerializedName("alias") val alias: String,
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("is_closed") val isClosed: Boolean,
    @SerializedName("url") val url: String,
    @SerializedName("review_count") val reviewCount: Int,
    @SerializedName("categories") val categories: List<Categories>,
    @SerializedName("rating") val rating: Float,
    @SerializedName("coordinates") val coordinates: Coordinates,
    @SerializedName("transactions") val transactions: List<String>,
    @SerializedName("price") val price: String,
    @SerializedName("location") val location: Location,
    @SerializedName("phone") val phone: String,
    @SerializedName("display_phone") val displayPhone: String,
    @SerializedName("distance") val distance: Float
)

data class BusinessRequiredData(val id: String, val imageUrl: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BusinessRequiredData> {
        override fun createFromParcel(parcel: Parcel): BusinessRequiredData {
            return BusinessRequiredData(parcel)
        }

        override fun newArray(size: Int): Array<BusinessRequiredData?> {
            return arrayOfNulls(size)
        }
    }
}