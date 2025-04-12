package org.example.project.common

import android.content.Context
import android.content.SharedPreferences

class SettingPreferencesAndroid(context: Context) : SettingPreferences {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    override fun getTheme(): Boolean {
        return sharedPreferences.getBoolean("darkTheme", true)
    }

    override fun changeTheme(isDarkTheme: Boolean) {
        sharedPreferences.edit().putBoolean("darkTheme", isDarkTheme).apply()
    }
}