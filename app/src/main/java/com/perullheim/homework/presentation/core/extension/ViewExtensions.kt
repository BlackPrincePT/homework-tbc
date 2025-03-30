package com.perullheim.homework.presentation.core.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(body: String) {
    Snackbar.make(this, body, Snackbar.LENGTH_SHORT).show()
}