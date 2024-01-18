package com.loryblu.core.network

object HttpRoutes {
    private const val BASE_URL = BuildConfig.BASE_URL
    const val REGISTER = "$BASE_URL/auth/register"
    const val RECOVERY_PASSWORD = "$BASE_URL/auth/recovery"
    const val NEW_PASSWORD = "$BASE_URL/auth/set-password"
    const val LOGIN = "$BASE_URL/auth/login"
    const val TASK = "$BASE_URL/task"
}
