package com.example.elixir

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object SecureStorage {

    private const val PREFERENCE_FILE = "secure_prefs"

    // Initialize EncryptedSharedPreferences
    fun getInstance(context: Context): EncryptedSharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            PREFERENCE_FILE,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        ) as EncryptedSharedPreferences
    }

    // Set whether it's the user's first time
    fun setFirstTimeUser(context: Context, isFirstTime: Boolean) {
        val prefs = getInstance(context)
        prefs.edit().putBoolean("isFirstTime", isFirstTime).apply()
    }

    // Get whether it's the user's first time
    fun isFirstTimeUser(context: Context): Boolean {
        val prefs = getInstance(context)
        return prefs.getBoolean("isFirstTime", true) // Default is true if not set
    }
}
