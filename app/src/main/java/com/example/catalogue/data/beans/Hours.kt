package com.example.catalogue.data.beans

import com.google.gson.annotations.SerializedName

data class Hours(
    @SerializedName("open") val open: List<Open>,
    @SerializedName("hours_type") val hoursType: String,
    @SerializedName("is_open_now") val isOpenNow: Boolean
)