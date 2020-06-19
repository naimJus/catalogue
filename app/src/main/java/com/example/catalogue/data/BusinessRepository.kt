package com.example.catalogue.data

import com.example.catalogue.data.beans.BusinessResponse
import com.example.catalogue.util.applySingleSchedulers
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class BusinessRepository @Inject constructor(private val apiInterface: ApiInterface) {


    fun callSearch(
        latitude: Double = 37.786882,
        longitude: Double = -122.399972,
        term: String? = null
    ): Single<Response<List<BusinessResponse>>> =
        apiInterface.businessSearch(latitude, longitude, term)
            .compose(applySingleSchedulers())
            .flatMap { Single.just(it) }
}