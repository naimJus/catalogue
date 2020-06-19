package com.example.catalogue.data.beans

import com.google.gson.annotations.SerializedName

data class Open (
	@SerializedName("is_overnight") val is_overnight : Boolean,
	@SerializedName("start") val start : Int,
	@SerializedName("end") val end : Int,
	@SerializedName("day") val day : Int
)