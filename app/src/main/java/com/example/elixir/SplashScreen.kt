package com.example.elixir

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.WindowCompat

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_splash_screen)

        // Find your CardView for the splash logo
        val splimg = findViewById<CardView>(R.id.SplLogo)

        // Load animation for the splash screen
        val splanim = AnimationUtils.loadAnimation(this, R.anim.anim_splash)

        // Start animation on the CardView
        splimg.startAnimation(splanim)
        val isFirstTime = SecureStorage.isFirstTimeUser(this)
        // Use Handler with Looper to delay the start of the next activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Create an Intent to start the Login activity
            if (isFirstTime) {
                // First time login, move to onboarding or login screen
                val intent = Intent(this, Login::class.java)
                SecureStorage.setFirstTimeUser(this, false) // Set first-time flag to false
                startActivity(intent)
            } else {
                // If not first time, go to the home screen or another activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            // Finish the current activity to prevent going back to it
        }, 2500) // Delay for 2.5 seconds (2500 milliseconds)
    }
}
