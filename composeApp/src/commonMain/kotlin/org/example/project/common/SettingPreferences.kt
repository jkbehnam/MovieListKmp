package org.example.project.common

interface SettingPreferences {
    fun getTheme(): Boolean
    fun changeTheme(isDarkTheme: Boolean)
}