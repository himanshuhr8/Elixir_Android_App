package storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SecureStorage(context: Context) {

    // Define or get the master key for encryption
    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    // Create encrypted shared preferences
    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_prefs",  // File name
        masterKeyAlias,  // Master key alias
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // Function to store data securely
    fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    // Function to retrieve data securely
    fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    // Function to remove data
    fun removeData(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}
