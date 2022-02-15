package com.example.practice.module.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.HomeInfoBean
import com.example.practice.bean.LeftMoneyBean
import com.example.practice.network.NetworkApi
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is pay Fragment"
    }
    val text: LiveData<String> = _text
    val leftMoneyLiveData = MutableLiveData<Result<LeftMoneyBean>>()

    fun getLeftMoneyData(){
        val leftMoneyData= NetworkApiTest("https://a4f77a93-cbb2-42c8-b457-8045938e6064.mock.pstmn.io")
        viewModelScope.launch {
            val requestValue=leftMoneyData.requestLeftMoney()
            leftMoneyLiveData.value=requestValue
        }
    }
}