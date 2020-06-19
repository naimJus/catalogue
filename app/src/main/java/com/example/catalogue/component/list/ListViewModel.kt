package com.example.catalogue.component.list

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun subscribe() {
        when (businessListData.value) {
            null -> fetchData()
        }
    }

    private fun fetchData() {
        disposable.add(repository.callSearch().subscribe(
            { response ->
                if (response.isSuccessful && !response.body()?.businesses.isNullOrEmpty()) {
                    _businessListData.value = response.body()?.businesses
                } else {
                    showErrorDialog()
                }
            },
            {
                showErrorDialog()
            }
        ))
    }

    fun itemSelected(view: ImageView, business: Business) {
        _navigateToDetailData.value =
            Event(view to BusinessRequiredData(business.id, business.imageUrl))
    }
}