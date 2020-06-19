package com.example.catalogue.data.beans

import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName("id") val id: String,
    @SerializedName("alias") val alias: String,
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("is_claimed") val isClaimed: Boolean,
    @SerializedName("is_closed") val is_closed: Boolean,
    @SerializedName("url") val url: String,
    @SerializedName("phone") val phone: Int,
    @SerializedName("display_phone") val displayPhone: String,
    @SerializedName("review_count") val reviewCount: Int,
    @SerializedName("categories") val categories: List<Categories>,
    @SerializedName("rating") val rating: Int,
    @SerializedName("location") val location: Location,
    @SerializedName("coordinates") val coordinates: Coordinates,
    @SerializedName("photos") val photos: List<String>,
    @SerializedName("price") val price: String,
    @SerializedName("hours") val hours: List<Hours>,
    @SerializedName("transactions") val transactions: List<String>
)