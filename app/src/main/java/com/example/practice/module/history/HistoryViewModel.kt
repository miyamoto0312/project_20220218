package com.example.practice.module.history

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.HistoryBean
import com.example.practice.network.NetworkApi
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {
    val historyListLiveData = MutableLiveData<Result<HistoryBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getHistoryList(startDate:String,endDate:String) {
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            // read data from networkapi
            val resultFromNetwork = NetworkApi.requestHistoryInfo(startDate,endDate)
            historyListLiveData.value=resultFromNetwork
            loadingLiveData.postValue(false)
        }
    }
}