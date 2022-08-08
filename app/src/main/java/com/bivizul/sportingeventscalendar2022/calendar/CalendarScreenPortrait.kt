package com.bivizul.sportingeventscalendar2022.calendar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bivizul.sportingeventscalendar2022.model.Event
import com.bivizul.sportingeventscalendar2022.model.Events
import io.github.boguszpawlowski.composecalendar.CalendarState
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import java.time.LocalDate

@Composable
fun CalendarScreenPortrait(
    state: CalendarState<DynamicSelectionState>,
    selectedId: Int,
    eventList: Events,
) {

    var event: Event

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.surface
    ) { paddingValues ->
        Column {
            SelectableCalendar(
                modifier = Modifier
                    .animateContentSize()
                    .padding(paddingValues),
                calendarState = state,
                dayContent = { dayState ->
                    EventDay(
                        state = dayState,
                        event = eventList.events.firstOrNull {
                            LocalDate.parse(it.date) == dayState.date
                        }
                    )
                },
                showAdjacentMonths = true
            )
            if (selectedId > 0) {
                event = eventList.events[selectedId - 1]
                CardEvent(event = event)
            }
        }
    }
}





