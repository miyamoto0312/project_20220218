package com.example.practice.module.pay

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PayViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is pay Fragment"
    }
    val text: LiveData<String> = _text

}