package com.loryblu.data.auth.repository

import android.util.Log

internal class RegisterRepositoryImpl : RegisterRepository {
    override fun registerUser() {
        Log.d("KoinTest", "calling registerUser")
    }
}