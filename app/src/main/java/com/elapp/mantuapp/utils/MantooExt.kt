package com.elapp.mantuapp.utils

import android.text.format.DateFormat
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun View.showSnackbar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun formattedDate(date: Date): String {
    val dateFormat = SimpleDateFormat("yyyy/mm/dd")
    val currentDate = dateFormat.format(date)
    return currentDate.toString()
}