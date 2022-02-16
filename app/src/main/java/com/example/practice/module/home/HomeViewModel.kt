package com.example.practice.module.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.HistoryBean
import com.example.practice.bean.HomeInfoBean
import com.example.practice.bean.LeftMoneyBean
import com.example.practice.network.NetworkApi
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val leftMoneyLiveData = MutableLiveData<Result<LeftMoneyBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getLeftMoneyData(){
        loadingLiveData.postValue(true)
        viewModelScope.launch {

            val resultFromNetwork = NetworkApi.requestLeftMoney()
            leftMoneyLiveData.value=resultFromNetwork
            loadingLiveData.postValue(false)
        }
    }

}