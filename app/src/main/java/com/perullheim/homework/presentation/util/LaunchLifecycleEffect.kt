package com.perullheim.homework.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LaunchLifecycleEffect(
    key: Any? = null,
    block: suspend CoroutineScope.() -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = lifecycleOwner, key2 = key) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            block.invoke(this)
        }
    }
}

@Composable
fun <T> CollectEffect(
    flow: Flow<T>,
    action: suspend (T) -> Unit
) {
    LaunchLifecycleEffect {
        flow.collect(action)
    }
}

@Composable
fun <T> CollectLatestEffect(
    flow: Flow<T>,
    action: suspend (T) -> Unit
) {
    LaunchLifecycleEffect {
        flow.collectLatest(action)
    }
}