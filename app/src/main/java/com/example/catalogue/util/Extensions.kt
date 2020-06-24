package com.example.catalogue.util

import android.annotation.SuppressLint
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

const val TIME_FORMAT = "HH:mm"

/**
 * Apply schedulers
 */
fun <T> Single<T>.applySingleSchedulers(): Single<T> =
    compose { observable: Single<T> ->
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

/**
 * Convert time represented as a 4 digit Int to String in the following format [TIME_FORMAT]
 *
 * Start and end  of the opening hours in a day, in 24-hour clock notation, like 1000 means 10 AM,
 * 2130 means 9:30 PM.
 */
@SuppressLint("SimpleDateFormat")
fun Int.toHourMinute(): String {
    val date = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, this@toHourMinute / 100)
        set(Calendar.MINUTE, this@toHourMinute % 100)
    }.time
    return SimpleDateFormat(TIME_FORMAT).format(date)
}
