package com.example.practice.network

import com.example.practice.network.base.BaseNetworkApi

class NetworkApiTest(url:String) : BaseNetworkApi<INetworkService>(url){
    //残高APIをコール
    suspend fun requestLeftMoney() = getResult {
        service.requestLeftMoney()
    }
}