package com.example.catalogue.data.beans

import com.google.gson.annotations.SerializedName

data class Center (
	@SerializedName("longitude") val longitude : Double,
	@SerializedName("latitude") val latitude : Double
)