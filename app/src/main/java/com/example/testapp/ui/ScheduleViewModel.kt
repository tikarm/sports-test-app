package com.example.testapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.Event
import com.example.testapp.domain.EventsLoadException
import com.example.testapp.domain.GetScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

private const val DATA_FETCH_INTERVAL_MILLISECONDS = 30 * 1000

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getScheduleUseCase: GetScheduleUseCase,
) : ViewModel() {

    private val _scheduleUiState = MutableStateFlow<EventsUiState>(EventsUiState.Progress)
    val scheduleUiState = _scheduleUiState.asStateFlow()

    init {
        startDataFetching()
    }

    fun loadSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _scheduleUiState.value = EventsUiState.Success(getScheduleUseCase.execute().map { it.toEventUiState() })
            } catch (e: EventsLoadException) {
                Log.e("ScheduleViewModel", "Load events failed", e)
                _scheduleUiState.value = EventsUiState.Error.DataLoad
            }
        }
    }

    private fun startDataFetching() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                loadSchedule()
            }
        }, 0, (DATA_FETCH_INTERVAL_MILLISECONDS).toLong())
    }

    private fun Event.toEventUiState(): EventUiState {
        return EventUiState(
            title, subtitle, date, imageUrl, videoUrl
        )
    }
}