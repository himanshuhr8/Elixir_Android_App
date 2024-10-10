package com.example.elixir

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.elixir.databinding.ActivityLoginBinding
import com.google.firebase.firestore.FirebaseFirestore
import storage.SecureStorage

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use PreCheck to handle login status check
        //PreCheck.checkLoginStatus(this)

        // Setup the login activity view after the check
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hide action bar and set full screen
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        setupSkipButton()
        setupLoginButton()
    }

    private fun setupSkipButton() {
        val skip = "Skip for now"
        val spanString = SpannableString(skip)
        val skipTv = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@Login, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        spanString.setSpan(skipTv, 0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.skipBtn.text = spanString
        binding.skipBtn.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setupLoginButton() {
        binding.loginBtn.setOnClickListener {
            val userEmail = binding.edtEmail.text.toString()

            if (userEmail.isNotEmpty()) {
                if (isValidEmail(userEmail)) {
                    saveUserLogin(userEmail)
                    com.example.elixir.SecureStorage.setFirstTimeUser(this, true)
                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    //PreCheck.checkLoginStatus(this) // Re-check login status
                } else {
                    Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter your Email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        return emailRegex.matches(email)
    }

    private fun saveUserLogin(userEmail: String) {
        val db = FirebaseFirestore.getInstance()
        val userEmailMap = hashMapOf("email" to userEmail)

        db.collection("users").add(userEmailMap)
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }

        // Save login status in SecureStorage
        //val secureStorage = SecureStorage(this)
        //secureStorage.saveData("firstTime", "true")
    }
}
