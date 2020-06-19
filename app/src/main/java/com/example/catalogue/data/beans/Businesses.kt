package com.example.catalogue.data.beans

import com.google.gson.annotations.SerializedName

data class Businesses(
    @SerializedName("id") val id: String,
    @SerializedName("alias") val alias: String,
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("is_closed") val isClosed: Boolean,
    @SerializedName("url") val url: String,
    @SerializedName("review_count") val reviewCount: Int,
    @SerializedName("categories") val categories: List<Categories>,
    @SerializedName("rating") val rating: Double,
    @SerializedName("coordinates") val coordinates: Coordinates,
    @SerializedName("transactions") val transactions: List<String>,
    @SerializedName("price") val price: String,
    @SerializedName("location") val location: Location,
    @SerializedName("phone") val phone: String,
    @SerializedName("display_phone") val displayPhone: String,
    @SerializedName("distance") val distance: Double
)