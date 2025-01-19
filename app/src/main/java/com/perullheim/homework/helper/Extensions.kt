package com.perullheim.homework.helper

import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(body: String) {
    Snackbar.make(this, body, Snackbar.LENGTH_LONG).show()
}

fun List<AppCompatEditText>.validateFields() : Boolean {
    return all { it.text.toString().isNotEmpty() }
}