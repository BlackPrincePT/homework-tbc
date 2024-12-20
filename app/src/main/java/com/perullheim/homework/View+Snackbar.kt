package com.perullheim.homework

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(body: String) {
    Snackbar.make(this, body, Snackbar.LENGTH_LONG).show()
}