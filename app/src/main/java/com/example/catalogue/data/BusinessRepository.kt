package com.example.catalogue.data

import com.example.catalogue.data.beans.BusinessResponse
import com.example.catalogue.data.beans.DetailsResponse
import com.example.catalogue.util.applySingleSchedulers
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class BusinessRepository @Inject constructor(private val apiInterface: ApiInterface) {

    private var businessResponse: BusinessResponse? = null
    private var detailsResponse: DetailsResponse? = null

    fun callSearch(
        forceRefresh: Boolean = false,
        latitude: Double = 37.786882,
        longitude: Double = -122.399972,
        term: String? = null
    ): Single<Response<BusinessResponse>> {
        return if (businessResponse == null || forceRefresh) {
            apiInterface.businessSearch(latitude, longitude, term)
                .applySingleSchedulers()
                .flatMap {
                    if (it.isSuccessful) {
                        businessResponse = it.body()
                    }
                    Single.just(it)
                }
        } else {
            Single.just(Response.success(businessResponse))
        }
    }

    fun callDetails(id: String, forceRefresh: Boolean = false): Single<Response<DetailsResponse>> {
        return if (forceRefresh || detailsResponse?.id != id) {
            apiInterface.businessDetails(id)
                .applySingleSchedulers()
                .flatMap {
                    if (it.isSuccessful) {
                        detailsResponse = it.body()
                    }
                    Single.just(it)
                }
        } else {
            Single.just(Response.success(detailsResponse!!))
        }
    }
}