package com.example.sewabisku.activities

import android.annotation.SuppressLint
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun rupiah(uang: Double): String{
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(uang).toString()
}

@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE, dd MMMM yyyy")
        .format(systemTime).toString()
}