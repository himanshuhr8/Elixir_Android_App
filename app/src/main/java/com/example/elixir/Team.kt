package com.example.elixir

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.elixir.databinding.ActivityTeamBinding

class Team : AppCompatActivity() {
    lateinit var binding: ActivityTeamBinding
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        dialog = Dialog(this)
        dialog.setContentView(R.layout.team_dialog)

        //Variables for elements in dialog box
        var teamLogo = dialog.findViewById<ImageView>(R.id.team_logo)
        var teamDescription = dialog.findViewById<TextView>(R.id.team_description)

        binding.gfgCardView.setOnClickListener{
            teamLogo.setImageResource(R.drawable.gfg)
            teamDescription.setText(R.string.gfg_description)
            dialog.show()
        }

        binding.codechefCardView.setOnClickListener{
            teamLogo.setImageResource(R.drawable.codechef)
            teamDescription.setText(R.string.cc_description)
            dialog.show()
        }

        binding.gdcsCardView.setOnClickListener {
            teamLogo.setImageResource(R.drawable.gdsc)
            teamDescription.setText(R.string.gdsc_description)
            dialog.show()
        }
    }
}