package com.example.testapp.domain

import com.example.testapp.util.DateUtils
import javax.inject.Inject

const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

class GetEventsUseCase @Inject constructor(
    private val repository: EventsRepository,
) {

    @Throws(EventsLoadException::class)
    suspend fun execute(): List<Event> {
        return repository.getEvents()
            .sortedBy {
                DateUtils.parseDateFromFormat(it.date, DATE_FORMAT)
            }
            .map {
                it.copy(date = DateUtils.formatDate(it.date, DATE_FORMAT))
            }
    }
}