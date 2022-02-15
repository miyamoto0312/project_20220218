package com.example.practice.bean

data class NotificationBean(
    val notificationList :List<NotificationData>
)

data class NotificationData(
    val title: String,
    val msg: String
)
