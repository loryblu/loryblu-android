package com.loryblu.util.extensions

import android.util.Patterns

fun String.isEmailValid() : Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()