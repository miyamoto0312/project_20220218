package com.example.practice.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryBean(
    var dataList: List<Data>
):Parcelable

@Parcelize
data class Data (
    var address: String,
    var date: String,
    var price: String
):Parcelable