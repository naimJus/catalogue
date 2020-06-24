package com.example.catalogue.component.list

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catalogue.R
import com.example.catalogue.component.BaseViewModel
import com.example.catalogue.data.BusinessRepository
import com.example.catalogue.data.Event
import com.example.catalogue.data.beans.Business
import com.example.catalogue.data.beans.BusinessRequiredData
import javax.inject.Inject


class ListViewModel @Inject constructor(private val repository: BusinessRepository) :
    BaseViewModel() {

    private val _businessListData = MutableLiveData<List<Business>>()
    val businessListData: LiveData<List<Business>>
        get() = _businessListData

    private val _navigateToDetailData = MutableLiveData<Event<Pair<View, BusinessRequiredData>>>()
    val navigateToDetailData: LiveData<Event<Pair<View, BusinessRequiredData>>>
        get() = _navigateToDetailData

    private val _searchExpandedLiveData = MutableLiveData<Event<Unit>>()
    val searchExpandedLiveData: LiveData<Event<Unit>>
        get() = _searchExpandedLiveData

    fun subscribe() {
        performSearch(false, "")
    }

    fun itemSelected(view: ImageView, business: Business) {
        _navigateToDetailData.value =
            Event(view to BusinessRequiredData(business.id, business.imageUrl))
    }

    fun query(query: String?) {
        performSearch(true, query)
    }

    fun searchActionViewState() {
        _searchExpandedLiveData.value = Event(Unit)
    }

    private fun performSearch(refresh: Boolean = false, term: String? = null) {
        disposable.add(repository.callSearch(forceRefresh = refresh, term = term).subscribe(
            { response ->
                if (response.isSuccessful) {
                    if (response.body()?.businesses.isNullOrEmpty()) {
                        showErrorDialog(message = R.string.no_data_message)
                    } else {
                        _businessListData.value = response.body()?.businesses
                    }
                } else {
                    showErrorDialog()
                }
            },
            {
                showErrorDialog()
            }
        ))
    }
}