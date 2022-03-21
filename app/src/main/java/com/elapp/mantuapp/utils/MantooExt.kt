package com.elapp.mantuapp.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}