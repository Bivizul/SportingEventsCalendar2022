package com.bivizul.sportingeventscalendar2022.other

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.bivizul.sportingeventscalendar2022.calendar.CalendarScreenLandscape
import com.bivizul.sportingeventscalendar2022.calendar.CalendarScreenPortrait
import com.bivizul.sportingeventscalendar2022.calendar.CalendarViewModel
import com.bivizul.sportingeventscalendar2022.util.Resource
import com.bivizul.sportingeventscalendar2022.util.Util
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode.Single

/**
 * In this sample, calendar composable is wired with an ViewModel. It's purpose is to show how to use
 * the composable in real world use-case, by an example implementation of a calendar
 * which can display planned recipes along with their prices
 */
@Composable
fun Calendar(
    viewModel: CalendarViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val resourceEvents by viewModel.events.collectAsState()
    val selectedId by viewModel.selectedEventsIdFlow.collectAsState(0)

    val state = rememberSelectableCalendarState(
        confirmSelectionChange = { viewModel.onSelectionChanged(it); true },
        initialSelectionMode = Single,
    )

    when (resourceEvents) {
        is Resource.Loading -> {}
        is Resource.Success -> {
            resourceEvents.data?.let { events ->
                val configuration = LocalConfiguration.current
                when (configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT ->
                        CalendarScreenPortrait(
                            state = state,
                            selectedId = selectedId,
                            eventList = events
                        )
                    // Landscape
                    else ->
                        CalendarScreenLandscape(
                            state = state,
                            selectedId = selectedId,
                            eventList = events
                        )
                }
            }
        }
        is Resource.Error -> {
            Util.getDialogErrorConnect(context, activity)
        }
    }
}

