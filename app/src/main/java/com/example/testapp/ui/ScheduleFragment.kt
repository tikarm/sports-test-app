package com.example.testapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.testapp.util.collectInLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : BaseFragment() {

    private val scheduleViewModel: ScheduleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()

        getSchedule()
    }

    private fun subscribeObservers() {
        scheduleViewModel.scheduleUiState.collectInLifecycle(
            viewLifecycleOwner,
            Lifecycle.State.CREATED
        ) { eventsUiState ->
            when (eventsUiState) {
                is EventsUiState.Progress -> {
                    binding.pbEventsFragment.visibility = View.VISIBLE
                }

                is EventsUiState.Error -> {
                    binding.pbEventsFragment.visibility = View.GONE
                    binding.tvErrorMessageEventsFragment.text = getString(eventsUiState.resId)
                }

                is EventsUiState.Success -> {
                    binding.pbEventsFragment.visibility = View.GONE
                    eventsAdapter.addItems(eventsUiState.events)
                }
            }
        }
    }

    private fun getSchedule() {
        scheduleViewModel.loadSchedule()
    }
}