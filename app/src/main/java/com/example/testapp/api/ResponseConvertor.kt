package com.example.testapp.api

import com.example.testapp.api.exception.ApiException
import com.example.testapp.api.exception.UnexpectedException
import retrofit2.Response

@Throws(ApiException::class, UnexpectedException::class)
inline fun <reified T> makeApiCall(apiCall: () -> Response<T>): T? {
    val response: Response<T> = try {
        apiCall.invoke()
    } catch (e: Exception) {
        throw UnexpectedException(e)
    }

    val statusCode = response.code()
    if (response.isSuccessful) {
        return response.body()
    } else {
        throw ApiException(
            "StatusCode: [$statusCode], error body: [${response.errorBody()?.string()}]"
        )
    }
}