package com.example.spliteasy.data.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SessionManager(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val securePrefs = EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveSession(name: String, email: String, profileImage: String?, token: String) {
        prefs.edit()
            .putString(KEY_NAME, name)
            .putString(KEY_EMAIL, email)
            .putString(KEY_PROFILE_IMAGE, profileImage)
            .apply()

        securePrefs.edit()
            .putString(KEY_TOKEN, token)
            .apply()
    }

    fun getUserName(): String? = prefs.getString(KEY_NAME, null)

    fun getUserEmail(): String? = prefs.getString(KEY_EMAIL, null)

    fun getProfileImage(): String? = prefs.getString(KEY_PROFILE_IMAGE, null)

    fun getAccessToken(): String? = securePrefs.getString(KEY_TOKEN, null)

    fun isLoggedIn(): Boolean = getAccessToken() != null

    fun clearSession() {
        prefs.edit().clear().apply()
        securePrefs.edit().clear().apply()
    }

    companion object {
        private const val KEY_NAME = "user_name"
        private const val KEY_EMAIL = "user_email"
        private const val KEY_PROFILE_IMAGE = "user_profile"
        private const val KEY_TOKEN = "access_token"
    }
}
