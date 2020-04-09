package com.groundzero.github.data

import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResultData<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ResultData.success(body)
            }
            return ResultData.failure(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return ResultData.failure(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ResultData<T> {
        return ResultData.failure("Network call has failed for a following reason: $message")
    }
}