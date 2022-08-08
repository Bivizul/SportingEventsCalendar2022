package com.bivizul.sportingeventscalendar2022.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bivizul.sportingeventscalendar2022.model.Event
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState

@Composable
fun EventDay(
    state: DayState<DynamicSelectionState>,
    event: Event?,
    modifier: Modifier = Modifier,
) {
    val date = state.date
    val selectionState = state.selectionState
    val isSelected = selectionState.isDateSelected(date)

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp),
        elevation = if (state.isFromCurrentMonth) 6.dp else 0.dp,
        border = if (state.isCurrentDay) {
            BorderStroke(3.dp, MaterialTheme.colors.secondary)
        } else if (isSelected) {
            BorderStroke(1.dp, MaterialTheme.colors.secondary)
        } else {
            null
        },
        backgroundColor = if (state.isFromCurrentMonth) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.primaryVariant
        },
        contentColor = if (isSelected) {
            MaterialTheme.colors.secondary
        } else {
            MaterialTheme.colors.onPrimary
        }
    ) {
        Column(
            modifier = Modifier
                .clickable { selectionState.onDateSelected(date) },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = date.dayOfMonth.toString())
            if (event != null) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.secondary)
                )
                Text(
                    text = event.sport,
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}