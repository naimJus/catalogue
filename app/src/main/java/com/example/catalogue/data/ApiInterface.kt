package com.example.catalogue.data

import com.example.catalogue.data.beans.BusinessResponse
import com.example.catalogue.util.AUTOCOMPLETE
import com.example.catalogue.util.BUSINESS_DETAILS
import com.example.catalogue.util.BUSINESS_SEARCH
import com.example.catalogue.util.REVIEWS
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(BUSINESS_DETAILS)
    fun businessDetails(@Path("id") id: String)

    @GET(REVIEWS)
    fun businessReviews(@Path("id") id: String)

    @GET(AUTOCOMPLETE)
    fun autocomplete(
        @Query("text") text: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    )

    @GET(BUSINESS_SEARCH)
    fun businessSearch(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("term") term: String?
        ): Single<Response<List<BusinessResponse>>>
}