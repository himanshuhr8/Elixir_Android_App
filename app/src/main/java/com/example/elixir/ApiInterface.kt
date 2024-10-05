package com.example.elixir

import com.example.elixir.event.EventData
import com.example.elixir.event.EventDataItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/events")
    fun getEventData(): Call<EventData>
}