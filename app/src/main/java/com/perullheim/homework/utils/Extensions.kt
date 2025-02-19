package com.perullheim.homework.utils

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.perullheim.homework.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun Fragment.viewLifecycleScope(block: suspend CoroutineScope.() -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            block.invoke(this)
        }
    }
}

fun <T> Fragment.collectLatest(flow: Flow<T>, action: suspend (value: T) -> Unit) {
    viewLifecycleScope {
        flow.collectLatest {
            action.invoke(it)
        }
    }
}

fun <T> Fragment.collect(flow: Flow<T>, action: suspend (value: T) -> Unit) {
    viewLifecycleScope {
        flow.collect {
            action.invoke(it)
        }
    }
}

fun ImageView.setImage(url: String) {
    Glide.with(this.context)
        .load(url.ifEmpty { null })
        .centerCrop()
        .into(this)
}

fun View.showSnackBar(body: String) {
    Snackbar.make(this, body, Snackbar.LENGTH_LONG).show()
}

fun List<AppCompatEditText>.validateFields(): Boolean {
    return all { it.text.toString().isNotEmpty() }
}