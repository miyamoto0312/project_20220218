package com.example.practice.bean

data class PayRequestBean(
    val date: String,
    val hash: String,
    val id_from: Int,
    val id_to: Long,
    val money: Int,
    val time: String
)