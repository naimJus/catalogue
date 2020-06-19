package com.example.catalogue.component.list

import com.example.catalogue.component.BaseViewModel
import com.example.catalogue.data.BusinessRepository
import javax.inject.Inject

class ListViewModel @Inject constructor(private val repository: BusinessRepository) :
    BaseViewModel() {


}