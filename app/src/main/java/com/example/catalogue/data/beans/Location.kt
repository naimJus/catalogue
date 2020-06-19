package com.example.catalogue.data.beans

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class Location(
    @SerializedName("address1") val address1: String?,
    @SerializedName("address2") val address2: String?,
    @SerializedName("address3") val address3: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("zip_code") val zip_code: Int,
    @SerializedName("country") val country: String?,
    @SerializedName("state") val state: String?,
    @SerializedName("display_address") val displayAddress: ArrayList<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address1)
        parcel.writeString(address2)
        parcel.writeString(address3)
        parcel.writeString(city)
        parcel.writeInt(zip_code)
        parcel.writeString(country)
        parcel.writeString(state)
        parcel.writeStringList(displayAddress)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}