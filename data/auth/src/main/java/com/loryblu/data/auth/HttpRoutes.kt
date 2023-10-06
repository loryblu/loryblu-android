package com.loryblu.data.auth

object HttpRoutes {
    private const val BASE_URL = BuildConfig.BASE_URL
    const val REGISTER = "$BASE_URL/auth/register"
    const val RECOVERY_PASSWORD = "$BASE_URL/auth/recovery"
}
