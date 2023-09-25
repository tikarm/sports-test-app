package com.example.testapp.data

import com.example.testapp.api.ApiService
import com.example.testapp.api.makeApiCall
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getEvents(): List<EventDto>? = makeApiCall {
        apiService.getEvents()
    }

    suspend fun getSchedule(): List<EventDto>? = makeApiCall {
        apiService.getEvents()
    }
}