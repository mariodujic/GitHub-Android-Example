package com.groundzero.github.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, C> resultLiveDataPersistant(
    networkCall: suspend () -> Result<C>,
    saveLocal: suspend (C) -> Unit,
    observeLocal: suspend () -> LiveData<T>
): LiveData<Result<T>> =

    liveData(Dispatchers.IO) {

        val source = observeLocal.invoke().map {
            Result.success(it)
        }
        emit(Result.loading())

        val responseStatus = networkCall.invoke()

        if (responseStatus.status == Result.Status.SUCCESS) {
            val networkSource = Result.success(responseStatus.data)
            saveLocal.invoke(networkSource.data!!)
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error(responseStatus.message!!))
        }

        emitSource(source)
    }

fun <T> resultLiveDataRemote(networkCall: suspend () -> Result<T>): LiveData<Result<T>> =

    liveData(Dispatchers.IO) {

        emit(Result.loading())
        val responseStatus = networkCall.invoke()

        if (responseStatus.status == Result.Status.SUCCESS) {
            Result.success(responseStatus.data!!)
            val source = Result.success(responseStatus.data)
            emit(source)
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error(responseStatus.message!!))
        }
    }