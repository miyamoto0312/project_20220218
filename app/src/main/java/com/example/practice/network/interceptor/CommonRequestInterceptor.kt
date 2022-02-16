package com.example.practice.network.interceptor

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class CommonRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        // 請求へーだー
        //builder.addHeader("brand", Build.BRAND)
        //builder.addHeader("model", Build.MODEL)
        builder.addHeader("x-api-key", "H3tjZHpvcx6hJE095pM5Z3ey1P6KKU6F8W3b3qjp")
        return chain.proceed(builder.build())
    }
}