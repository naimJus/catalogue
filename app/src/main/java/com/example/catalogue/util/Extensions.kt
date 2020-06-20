package com.example.catalogue.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.catalogue.R
import com.example.catalogue.component.detail.DetailsViewModel
import com.example.catalogue.component.detail.GalleryAdapter
import com.example.catalogue.component.list.BusinessAdapter
import com.example.catalogue.component.list.ListViewModel
import com.example.catalogue.data.beans.Business
import com.example.catalogue.data.beans.Categories
import com.example.catalogue.data.beans.Hours
import com.example.catalogue.data.beans.Location
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

fun <T> Single<T>.applySingleSchedulers(): Single<T> =
    compose { observable: Single<T> ->
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

@BindingAdapter(value = ["businessItems", "businessViewModel"], requireAll = true)
fun items(
    recyclerView: RecyclerView,
    list: List<Business>?,
    listViewModel: ListViewModel
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = BusinessAdapter(listViewModel, list)
    } else {
        (recyclerView.adapter as BusinessAdapter).list = list
        (recyclerView.adapter as BusinessAdapter).notifyDataSetChanged()
    }
    recyclerView.isVisible = !list.isNullOrEmpty()
}

@BindingAdapter(value = ["galleryItems", "detailsViewModel"], requireAll = true)
fun galleryItems(
    recyclerView: RecyclerView,
    list: List<String>?,
    detailsViewModel: DetailsViewModel
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = GalleryAdapter(detailsViewModel, list)
    } else {
        (recyclerView.adapter as GalleryAdapter).galleryItems = list
    }
    (recyclerView.adapter as GalleryAdapter).notifyDataSetChanged()

    recyclerView.isInvisible = list.isNullOrEmpty()
}

@BindingAdapter(value = ["drawableUrl"])
fun setDrawableUrl(view: ImageView, url: String?) {
    url?.let { view.load(it) }
}

@BindingAdapter(value = ["rating"])
fun rating(view: TextView, rating: Double) {
    view.visibility = if (rating > 0) View.VISIBLE else View.INVISIBLE
    view.text = rating.toString()
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["address"])
fun address(view: TextView, location: Location?) {
    if (location == null) {
        view.visibility = View.INVISIBLE
    } else {
        val string = view.context.getString(
            R.string.address,
            location.displayAddress?.get(0),
            location.displayAddress?.get(1),
            location.crossStreets
        )
        view.text = string
        view.visibility = View.VISIBLE
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["setPrice", "setCategories"], requireAll = true)
fun setPrice(view: TextView, price: String?, categories: List<Categories>?) {
    view.text = "$price ${categories?.joinToString(separator = ", ")}"
}

@SuppressLint("SetTextI18n")
@BindingAdapter(value = ["setDays"])
fun setDays(view: TextView, hours: List<Hours>?) {
    val schedule = StringBuilder()
    val daysArray = view.context.resources.getStringArray(R.array.days)
    val hoursArray = hours?.get(0)?.open
    hoursArray?.forEach { open ->
        schedule.append(
            String.format(
                daysArray[open.day], open.start.toHourMinute(), open.end.toHourMinute()
            )
        )
    }
    view.text = schedule.toString()
}

/**
 * Start and end  of the opening hours in a day, in 24-hour clock notation, like 1000 means 10 AM,
 * 2130 means 9:30 PM.
 */
@SuppressLint("SimpleDateFormat")
fun Int.toHourMinute(): String {
    val date = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, this@toHourMinute / 100)
        set(Calendar.MINUTE, this@toHourMinute % 100)
    }.time
    return SimpleDateFormat("HH:mm").format(date)
}
