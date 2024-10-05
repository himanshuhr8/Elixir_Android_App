package com.example.elixir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.elixir.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val skip = "Skip for now"
        val spanString = SpannableString(skip)
        val skipTv = object : ClickableSpan(){
            override fun onClick(widget: View) {
                Toast.makeText(this@Login,"Skipped", Toast.LENGTH_SHORT).show()
                intent = Intent(this@Login,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        spanString.setSpan(skipTv, 0,12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.skipBtn.text = spanString
        binding.skipBtn.movementMethod = LinkMovementMethod.getInstance()


        binding.loginBtn.setOnClickListener{
           if(binding.edtEmail.text.toString().isNotEmpty())
           {
              val intent = Intent(this,MainActivity::class.java)
               startActivity(intent)
               finish()
           }
           else
           {
               Toast.makeText(this,"Please enter your Email",Toast.LENGTH_SHORT).show()
           }
       }
    }
}