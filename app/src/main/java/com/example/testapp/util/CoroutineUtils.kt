package com.example.testapp.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectInLifecycle(
    owner: LifecycleOwner,
    state: Lifecycle.State,
    consumer: suspend CoroutineScope.(T) -> Unit
) {
    owner.lifecycleScope.launch {
        owner.repeatOnLifecycle(state) {
            collect { item ->
                consumer(item)
            }
        }
    }
}
