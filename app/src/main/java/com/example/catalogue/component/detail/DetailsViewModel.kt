package com.example.catalogue.component.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catalogue.component.BaseViewModel
import com.example.catalogue.data.BusinessRepository
import com.example.catalogue.data.beans.BusinessRequiredData
import com.example.catalogue.data.beans.DetailsResponse
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val repository: BusinessRepository) :
    BaseViewModel() {

    private val _businessDetailsLiveData = MutableLiveData<DetailsResponse>()
    val businessDetailsLiveData: LiveData<DetailsResponse>
        get() = _businessDetailsLiveData

    private val _businessRequiredLiveData = MutableLiveData<BusinessRequiredData>()
    val businessRequiredLiveData: LiveData<BusinessRequiredData>
        get() = _businessRequiredLiveData

    fun subscribe(business: BusinessRequiredData) {
        _businessRequiredLiveData.value = business
        disposable.add(repository.callDetails(business.id).subscribe(
            { response ->
                if (response.isSuccessful) {
                    _businessDetailsLiveData.value = response.body()
                } else {
                    showErrorDialog()
                }
            },
            { showErrorDialog() }
        ))
    }
}