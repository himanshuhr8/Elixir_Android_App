package com.example.elixir.event

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elixir.ApiInterface
import com.example.elixir.databinding.ActivityEventBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Event : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var eventListAdapter: EventListAdapter

    private lateinit var binding: ActivityEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        recyclerView = binding.eventList

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://elixir-backendv2.vercel.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getEventData()

        retrofitData.enqueue(object : Callback<EventData> {
            override fun onResponse(p0: Call<EventData>, p1: Response<EventData>) {

                if(p1.isSuccessful) {
                    Toast.makeText(this@Event, "Success", Toast.LENGTH_SHORT).show()
                    val data = p1.body()!!
                    for (i in data) {

                        eventListAdapter = EventListAdapter(this@Event, data)
                        recyclerView.adapter = eventListAdapter
                        recyclerView.layoutManager = LinearLayoutManager(this@Event)
                    }
                }
                else {
                    Toast.makeText(this@Event, "Failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<EventData>, p1: Throwable) {
                Toast.makeText(this@Event, "Failed to get data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}