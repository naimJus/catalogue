package com.example.catalogue.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.catalogue.component.list.BusinessAdapter
import com.example.catalogue.component.list.ListViewModel
import com.example.catalogue.data.beans.Businesses
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> applySingleSchedulers(): SingleTransformer<T, T>? {
    return SingleTransformer { observable: Single<T> ->
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

@BindingAdapter(value = ["items", "businessViewModel"], requireAll = true)
fun items(
    recyclerView: RecyclerView,
    list: List<Businesses>?,
    searchViewModel: ListViewModel
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter = BusinessAdapter(searchViewModel, list)
    } else {
        (recyclerView.adapter as BusinessAdapter).list = list
    }
    (recyclerView.adapter as BusinessAdapter).notifyDataSetChanged()

    recyclerView.isVisible = !list.isNullOrEmpty()
}

@BindingAdapter(value = ["drawableUrl"])
fun setDrawableUrl(view: ImageView, url: String) {
    view.load(url)
}

@BindingAdapter(value = ["rating"])
fun rating(view: TextView, rating: Double) {
    view.text = rating.toString()
}
