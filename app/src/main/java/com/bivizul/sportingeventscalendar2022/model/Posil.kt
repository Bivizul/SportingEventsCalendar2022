package com.bivizul.sportingeventscalendar2022.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Posil(
    @SerializedName("url")
    val posil: String,
)
