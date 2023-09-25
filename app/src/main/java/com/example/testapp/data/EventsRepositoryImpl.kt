package com.example.testapp.data

import com.example.testapp.api.exception.ApiException
import com.example.testapp.api.exception.UnexpectedException
import com.example.testapp.domain.Event
import com.example.testapp.domain.EventsLoadException
import com.example.testapp.domain.EventsRepository
import javax.inject.Inject
import kotlin.jvm.Throws

class EventsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : EventsRepository {

    @Throws(EventsLoadException::class)
    override suspend fun getEvents(): List<Event> {
        try {
            return remoteDataSource.getEvents()?.map { it.toEvent() } ?: emptyList()
        } catch (e: ApiException) {
            throw EventsLoadException(e)
        } catch (e: UnexpectedException) {
            throw EventsLoadException(e.cause ?: e)
        }
    }

    override suspend fun getSchedule(): List<Event> {
        try {
            return remoteDataSource.getSchedule()?.map { it.toEvent() } ?: emptyList()
        } catch (e: ApiException) {
            throw EventsLoadException(e)
        } catch (e: UnexpectedException) {
            throw EventsLoadException(e.cause ?: e)
        }
    }

    private fun EventDto.toEvent(): Event = Event(
        title ?: "",
        subtitle ?: "",
        date ?: "",
        imageUrl ?: "",
        videoUrl ?: "",
    )
}