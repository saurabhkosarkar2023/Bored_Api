package com.example.board_api.dashboard.data

import android.content.Context
import androidx.browser.trusted.Token
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SecurePrefs{
    private const val PREFS_NAME = "secure_prefs"
    private const val KEY_TOKEN = "auth_token"

    fun getEncryptedPrefs(context: Context)= EncryptedSharedPreferences.create(
        context,
        PREFS_NAME,
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(context: Context,token: String){
        getEncryptedPrefs(context).edit().putString(KEY_TOKEN,token).apply()
    }

    fun getToken(context: Context):String?{
        return getEncryptedPrefs(context).getString(KEY_TOKEN, null)
    }

    fun deleteToken(context: Context){
        return getEncryptedPrefs(context).edit().remove(KEY_TOKEN).apply()
    }
}