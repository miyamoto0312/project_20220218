package com.example.practice.network



import com.example.practice.bean.*
import com.example.practice.network.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface INetworkService {

    @GET("dev/homeinfo")
    suspend fun requestHomeInfo(): BaseResponse<HomeInfoBean>

    @GET("dev/history")
    suspend fun requestHistoryInfo(@Query("start") startDate: String,@Query("end") endDate: String): BaseResponse<HistoryBean>

    @GET("dev/left_money")
    suspend fun requestLeftMoney(): BaseResponse<LeftMoneyBean>

    @GET("dev/notification_data")
    suspend fun requestNotificationInfo(): BaseResponse<NotificationBean>

    @POST("dev/pay")
    suspend fun requestPaymentInfo(@Body request:PayRequestBean): BaseResponse<PayResponseBean>
}