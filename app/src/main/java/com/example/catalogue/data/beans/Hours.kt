package com.example.catalogue.data.beans

import com.google.gson.annotations.SerializedName

data class Hours(
    @SerializedName("open") val open: List<Open>,
    @SerializedName("hours_type") val hours_type: String,
    @SerializedName("is_open_now") val is_open_now: Boolean
)