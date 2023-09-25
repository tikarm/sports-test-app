package com.example.testapp.domain

import com.example.testapp.util.DateUtils
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val repository: EventsRepository,
) {

    @Throws(EventsLoadException::class)
    suspend fun execute(): List<Event> {
        return repository.getSchedule().filter {
            DateUtils.isTomorrowDate(it.date, DATE_FORMAT)
        }.sortedBy {
            DateUtils.parseDateFromFormat(it.date, DATE_FORMAT)
        }
    }
}