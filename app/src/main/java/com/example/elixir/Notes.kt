package com.example.elixir

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.elixir.databinding.ActivityNotesBinding

class Notes : AppCompatActivity() {
    lateinit var binding: ActivityNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.firstYear.setOnClickListener {
            openUrl("https://drive.google.com/drive/folders/1TTjSSShjK5nrUURnSIVOK1wnO-CBCp7R")
        }

        binding.secondYear.setOnClickListener {
            openUrl("https://drive.google.com/drive/folders/1c2gZ8TJ53P3ecNETMB403lTueQY5pEHU")
        }

        binding.thirdYear.setOnClickListener {
            openUrl("https://drive.google.com/drive/folders/1jUHgBPZdN5V5Am6gEHpHNTc4XWIQgC_W")
        }

        binding.forthYear.setOnClickListener {
            openUrl("https://drive.google.com/drive/folders/1LdrCdkcasmu_qA0vOK-UdI8UeThNhZ2C")
        }

    }

    fun openUrl(link:String){
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)
    }
}