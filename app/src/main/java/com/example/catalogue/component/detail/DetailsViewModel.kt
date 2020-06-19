package com.example.catalogue.component.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catalogue.component.BaseViewModel
import com.example.catalogue.data.BusinessRepository
import com.example.catalogue.data.Event
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

    private val _businessPhoneCallLiveData = MutableLiveData<Event<String>>()
    val businessPhoneCallLiveData: LiveData<Event<String>>
        get() = _businessPhoneCallLiveData

    private val _businessWebLiveData = MutableLiveData<Event<String>>()
    val businessWebLiveData: LiveData<Event<String>>
        get() = _businessWebLiveData

    lateinit var businessDetails: DetailsResponse

    fun subscribe(business: BusinessRequiredData) {
        _businessRequiredLiveData.value = business
        disposable.add(repository.callDetails(business.id).subscribe(
            { response ->
                if (response.isSuccessful && response.body() != null) {
                    businessDetails = response.body()!!
                    _businessDetailsLiveData.value = businessDetails
                } else {
                    showErrorDialog()
                }
            },
            { showErrorDialog() }
        ))
    }

    fun galleryItemClick(itemUrl: String) {
        _businessRequiredLiveData.value =
            BusinessRequiredData(businessDetailsLiveData.value!!.id, itemUrl)
    }

    fun callBusinessPhone() {
        _businessPhoneCallLiveData.value = Event(businessDetails.phone)
    }

    fun openInWeb() {
        _businessWebLiveData.value = Event(businessDetails.url)
    }
}