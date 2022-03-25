package com.elapp.mantuapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

fun View.showSnackbar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun formattedDate(date: Date): String {
    val dateFormat = SimpleDateFormat("yyyy/M/dd")
    val currentDate = dateFormat.format(date)
    return currentDate.toString()
}

fun dateFormatter(date: LocalDate): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val currentDate = dateFormat.format(date)
    return currentDate.toString()
}

fun dayFormatter(date: Date): String {
    val dateFormat = SimpleDateFormat("EEEE")
    return dateFormat.format(date)
}

fun dateFormatToGetDay(date: Date): String {
    val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy")
    return dateFormat.format(date)
}

fun dateFormatToGetDay(date: LocalDate): String {
    val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy")
    return dateFormat.format(date)
}