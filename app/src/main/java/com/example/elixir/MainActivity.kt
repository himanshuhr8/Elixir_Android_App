package com.example.elixir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.elixir.databinding.ActivityMainBinding
import com.example.elixir.event.Event

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.home.setOnClickListener {
            val intent = Intent(this,Home::class.java)
            startActivity(intent)
        }
        binding.event.setOnClickListener {
            val intent = Intent(this, Event::class.java)
            startActivity(intent)
        }
        binding.team.setOnClickListener {
            val intent = Intent(this,Team::class.java)
            startActivity(intent)
        }
        binding.notes.setOnClickListener {
            val intent = Intent(this,Notes::class.java)
            startActivity(intent)
        }
    }
}