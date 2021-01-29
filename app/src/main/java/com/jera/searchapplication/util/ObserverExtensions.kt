package com.jera.searchapplication.util

import androidx.lifecycle.LiveData

/**
 * Observes a [LiveData] forever for testing
 */

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}