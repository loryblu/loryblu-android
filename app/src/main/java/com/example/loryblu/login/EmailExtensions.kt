package com.example.loryblu.login

import android.util.Patterns

fun String.isEmailValid() : Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()