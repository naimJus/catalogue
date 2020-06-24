package com.example.catalogue.util

import com.example.catalogue.BuildConfig

const val BASE_URL = "https://api.yelp.com/v3/"

const val BUSINESS_SEARCH = "businesses/search"
const val BUSINESS_DETAILS = "businesses/{id}"
const val AUTOCOMPLETE = "autocomplete"
const val REVIEWS = "businesses/{id}/reviews"

const val TOKEN = BuildConfig.API_KEY