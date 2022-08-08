package com.bivizul.sportingeventscalendar2022.api

import com.bivizul.sportingeventscalendar2022.model.Events
import com.bivizul.sportingeventscalendar2022.model.Langag
import com.bivizul.sportingeventscalendar2022.model.Posil
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @GET("19SportingEventsCalendar2022/events.json")
    suspend fun getEvents(): Response<Events>

    @POST("19SportingEventsCalendar2022/posil.php")
    suspend fun getPosil(@Body langag: Langag): Response<Posil>

}