package com.perullheim.homework.helper

import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import com.google.android.material.snackbar.Snackbar

val AppCompatEditText.currentData
    get() = this.id to this.text.toString()

val AppCompatSpinner.currentData
    get() = this.id to this.selectedItem.toString()

fun View.showSnackBar(body: String) {
    Snackbar.make(this, body, Snackbar.LENGTH_LONG).show()
}