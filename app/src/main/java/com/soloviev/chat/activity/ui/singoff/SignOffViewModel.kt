package com.soloviev.chat.activity.ui.singoff

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignOffViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Are you sure to log out?"
    }
    val text: LiveData<String> = _text
}