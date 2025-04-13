package org.example.project.common

class JvmSystemInfo : SystemInfo {
    override fun getEnvironmentVariable(name: String): String? = System.getenv(name)
    override fun getSystemProperty(name: String): String? = System.getProperty(name)
}

actual fun getSystemInfo(): SystemInfo = JvmSystemInfo() 