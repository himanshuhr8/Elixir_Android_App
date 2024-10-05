package com.example.elixir

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.elixir.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.dsa.setOnClickListener {
            openUrl("https://neetcode.io/practice")
        }

        binding.buildYourOwnProject.setOnClickListener {
            openUrl("https://codingchallenges.fyi/challenges/intro/")
        }

        binding.discord.setOnClickListener{
            openUrl("https://discord.com/invite/YN9ZGjXdXK")
        }

        dialog = Dialog(this)
        dialog.setContentView(R.layout.reach_us)

        var reachUsSend = dialog.findViewById<Button>(R.id.reach_us_send)
        var reachUsName = dialog.findViewById<EditText>(R.id.reach_us_name)
        var reachUsEmail = dialog.findViewById<EditText>(R.id.reach_us_email)

        binding.reachUs.setOnClickListener {

        }
    }

    fun openUrl(link:String){
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)
    }
}