package com.example.catalogue.component.list

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catalogue.component.BaseViewModel
import com.example.catalogue.data.BusinessRepository
import com.example.catalogue.data.Event
import com.example.catalogue.data.beans.Businesses
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repository: BusinessRepository) :
    BaseViewModel() {

    private val _businessListData = MutableLiveData<List<Businesses>>()
    val businessListData: LiveData<List<Businesses>>
        get() = _businessListData

    private val _navigateToDetailData = MutableLiveData<Event<Pair<View, String>>>()
    val navigateToDetailData: LiveData<Event<Pair<View, String>>>
        get() = _navigateToDetailData


    fun subscribe() {
        disposable.add(repository.callSearch().subscribe(
            { response ->
                if (response.isSuccessful && !response.body()?.businesses.isNullOrEmpty()) {
                    _businessListData.value = response.body()?.businesses
                } else {
                    TODO("Not implemented")
                }
            },
            {
                TODO("Not implemented")
            }
        ))
    }

    fun itemSelected(pair: Pair<View, String>) {
        _navigateToDetailData.value = Event(pair)
    }


}