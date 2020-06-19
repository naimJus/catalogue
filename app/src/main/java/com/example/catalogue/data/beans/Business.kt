package com.example.catalogue.data.beans

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Business(
    @SerializedName("id") val id: String?,
    @SerializedName("alias") val alias: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("is_closed") val isClosed: Boolean,
    @SerializedName("url") val url: String?,
    @SerializedName("review_count") val reviewCount: Int,
    @SerializedName("categories") val categories: List<Categories>,
    @SerializedName("rating") val rating: Double,
    @SerializedName("coordinates") val coordinates: Coordinates,
    @SerializedName("transactions") val transactions: List<String>,
    @SerializedName("price") val price: String?,
    @SerializedName("location") val location: Location,
    @SerializedName("phone") val phone: String?,
    @SerializedName("display_phone") val displayPhone: String?,
    @SerializedName("distance") val distance: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt(),
        parcel.createTypedArrayList(Categories)!!,
        parcel.readDouble(),
        parcel.readParcelable<Coordinates>(Coordinates::class.java.classLoader) as Coordinates,
        parcel.createStringArrayList()!!,
        parcel.readString(),
        parcel.readParcelable<Location>(Location::class.java.classLoader) as Location,
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(alias)
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeByte(if (isClosed) 1 else 0)
        parcel.writeString(url)
        parcel.writeInt(reviewCount)
        parcel.writeTypedList(categories)
        parcel.writeDouble(rating)
        parcel.writeParcelable(coordinates, flags)
        parcel.writeStringList(transactions)
        parcel.writeString(price)
        parcel.writeParcelable(location, flags)
        parcel.writeString(phone)
        parcel.writeString(displayPhone)
        parcel.writeDouble(distance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Business> {
        override fun createFromParcel(parcel: Parcel): Business {
            return Business(parcel)
        }

        override fun newArray(size: Int): Array<Business?> {
            return arrayOfNulls(size)
        }
    }
}