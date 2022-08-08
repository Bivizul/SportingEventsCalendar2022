package com.bivizul.sportingeventscalendar2022.model

import androidx.annotation.Keep

@Keep
data class Event(
    val id: Int,
    val date: String,
    val time: String,
    val sport: String,
    val eventTitle: String,
    val location: String,
    val eventInfo: EventInfo,
)