package com.bivizul.sportingeventscalendar2022.data

import com.bivizul.sportingeventscalendar2022.api.Api
import com.bivizul.sportingeventscalendar2022.model.Langag
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api) {

    suspend fun getEvents() = api.getEvents()

    suspend fun getPosil(langag: Langag) = api.getPosil(langag)

}