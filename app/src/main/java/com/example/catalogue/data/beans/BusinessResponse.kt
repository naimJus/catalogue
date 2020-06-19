package com.example.catalogue.data.beans

import com.google.gson.annotations.SerializedName

data class BusinessResponse(
    @SerializedName("businesses") val businesses: List<Businesses>,
    @SerializedName("total") val total: Int,
    @SerializedName("region") val region: Region
)