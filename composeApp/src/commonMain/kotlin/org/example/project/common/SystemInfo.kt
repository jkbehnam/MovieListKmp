package org.example.project.common

interface SystemInfo {
    fun getEnvironmentVariable(name: String): String?
    fun getSystemProperty(name: String): String?
}

expect fun getSystemInfo(): SystemInfo 