package com.groundzero.github.utils

import java.text.SimpleDateFormat
import java.util.*

fun parseServerTime(time: String): String {
    val timezone = TimeZone.getTimeZone("UTC")
    val serverFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    serverFormat.timeZone = timezone
    val serverDate = serverFormat.parse(time)
    val clientFormat = SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.getDefault())
    return clientFormat.format(serverDate!!)
}