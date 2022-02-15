package com.example.practice.bean

data class LeftMoneyBean(
    val leftData : LeftData

)
data class LeftData(
    val date: String,
    val money: String
)