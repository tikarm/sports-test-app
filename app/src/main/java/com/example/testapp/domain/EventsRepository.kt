package com.example.testapp.domain

interface EventsRepository {
    suspend fun getEvents(): List<Event>

    suspend fun getSchedule(): List<Event>
}