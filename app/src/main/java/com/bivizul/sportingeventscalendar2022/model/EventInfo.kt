package com.bivizul.sportingeventscalendar2022.model

import androidx.annotation.Keep

@Keep
data class EventInfo(
    val title: String,
    val image: String,
    val subtitle1: String,
    val subtitle2: String,
)