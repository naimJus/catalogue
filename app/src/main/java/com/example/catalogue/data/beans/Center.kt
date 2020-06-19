package com.example.catalogue.data.beans

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Center (
	@SerializedName("longitude") val longitude : Double,
	@SerializedName("latitude") val latitude : Double
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readDouble(),
		parcel.readDouble()
	)
	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeDouble(longitude)
		parcel.writeDouble(latitude)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Center> {
		override fun createFromParcel(parcel: Parcel): Center {
			return Center(parcel)
		}

		override fun newArray(size: Int): Array<Center?> {
			return arrayOfNulls(size)
		}
	}
}