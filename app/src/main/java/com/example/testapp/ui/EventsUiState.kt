package com.example.testapp.ui

import androidx.annotation.StringRes
import com.example.testapp.R

sealed class EventsUiState {
    object Progress : EventsUiState()
    class Success(val events: List<EventUiState>) : EventsUiState()
    sealed class Error(@StringRes val resId: Int) : EventsUiState() {
        object Network : Error(R.string.network_error)
        object DataLoad : Error(R.string.data_load_error)
    }
}