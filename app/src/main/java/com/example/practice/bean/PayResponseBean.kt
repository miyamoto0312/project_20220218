package com.example.practice.bean

import java.io.Serializable

data class PayResponseBean(
    val payInfo: PaymentInfo
): Serializable
data class PaymentInfo(
    val date: String,
    val hash: String,
    val id_from: Int,
    val id_to: Long,
    val money: Int,
    val msg: String,
    val success: Boolean,
    val time: String
): Serializable

