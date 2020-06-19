package com.example.catalogue.component.list

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catalogue.R
import com.example.catalogue.component.BaseViewModel
import com.example.catalogue.component.info.DialogView
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

    private val _errorData = MutableLiveData<Event<DialogView>>()
    val errorData: LiveData<Event<DialogView>>
        get() = _errorData


    fun subscribe() {
        disposable.add(repository.callSearch().subscribe(
            { response ->
                if (response.isSuccessful && !response.body()?.businesses.isNullOrEmpty()) {
                    showErrorDialog()
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

    fun itemSelected(pair: Pair<View, String>) {
        _navigateToDetailData.value = Event(pair)
    }

    private fun showErrorDialog(
        title: Int = R.string.common_error_title,
        message: Int = R.string.common_error_msg,
        buttonRes: Int = R.string.word_ok
    ) {
        _errorData.value =
            Event(DialogView(title = title, message = message, buttonRes = buttonRes))
    }

}