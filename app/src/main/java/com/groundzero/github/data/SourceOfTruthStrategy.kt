package com.groundzero.github.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, C> resultLiveDataPersistent(
    networkCall: suspend () -> ResultData<C>,
    saveLocal: suspend (C) -> Unit,
    observeLocal: (suspend () -> LiveData<T>)
): LiveData<ResultData<T>> = liveData(Dispatchers.IO) {

    emit(ResultData.loading(null))

    val source = observeLocal.invoke().map {
        ResultData.success(it)
    }
    when (val responseStatus = networkCall.invoke()) {
        is ResultData.Success -> if(responseStatus.value != null) {
            saveLocal.invoke(responseStatus.value)
        }
        is ResultData.Failure -> {
            emit(ResultData.failure<T>(responseStatus.message))
        }
        is ResultData.Loading -> emit(ResultData.loading(null))
    }
    emitSource(source)
}