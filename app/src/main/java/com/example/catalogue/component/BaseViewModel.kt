package com.example.catalogue.component

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catalogue.R
import com.example.catalogue.component.info.DialogView
import com.example.catalogue.data.Event
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val disposable = CompositeDisposable()
    protected val _errorData = MutableLiveData<Event<DialogView>>()
    val errorData: LiveData<Event<DialogView>>
        get() = _errorData


    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    protected fun showErrorDialog(
        title: Int = R.string.common_error_title,
        message: Int = R.string.common_error_msg,
        buttonRes: Int = R.string.word_ok
    ) {
        _errorData.value =
            Event(DialogView(title = title, message = message, buttonRes = buttonRes))
    }
}