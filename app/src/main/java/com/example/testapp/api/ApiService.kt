package com.example.testapp.api

import com.example.testapp.data.EventDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("getEvents")
    suspend fun getEvents(): Response<List<EventDto>>

    @GET("getSchedule")
    suspend fun getSchedule(): Response<List<EventDto>>
}
