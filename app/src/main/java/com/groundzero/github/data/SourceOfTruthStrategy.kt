package com.groundzero.github.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, C> resultLiveDataPersistent(
    networkCall: suspend () -> Result<C>,
    saveLocal: suspend (C) -> Unit,
    observeLocal: suspend () -> LiveData<T>,
    removeLocal: (suspend () -> Unit)? = null
): LiveData<Result<T>> =

    liveData(Dispatchers.IO) {

        val source = observeLocal.invoke().map {
            Result.success(it)
        }
        emit(Result.loading())

        val responseStatus = networkCall.invoke()

        if (responseStatus.status == Result.Status.SUCCESS) {
            val networkSource = Result.success(responseStatus.data)
            if (responseStatus.data != null) {
                removeLocal?.invoke()
                saveLocal.invoke(networkSource.data!!)
            }
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error(responseStatus.message!!))
        }

        emitSource(source)
    }