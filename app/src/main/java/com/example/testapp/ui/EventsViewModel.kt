package com.example.testapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.Event
import com.example.testapp.domain.EventsLoadException
import com.example.testapp.domain.GetEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val getEventsUseCase: GetEventsUseCase,
) : ViewModel() {

    private val eventList = mutableListOf<EventUiState>()

    private val _eventsUiState = MutableStateFlow<EventsUiState>(EventsUiState.Progress)
    val eventsUiState = _eventsUiState.asStateFlow()

    fun loadEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                eventList.addAll(getEventsUseCase.execute().map { it.toEventUiState() })
                _eventsUiState.value = EventsUiState.Success(eventList)
            } catch (e: EventsLoadException) {
                Log.e("EventsViewModel", "Load events failed", e)
                _eventsUiState.value = EventsUiState.Error.DataLoad
            }
        }
    }

    private fun Event.toEventUiState(): EventUiState {
        return EventUiState(
            title, subtitle, date, imageUrl, videoUrl
        )
    }
}