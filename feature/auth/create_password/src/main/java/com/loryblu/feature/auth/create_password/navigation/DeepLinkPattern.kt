package com.loryblu.feature.auth.create_password.navigation

import android.net.Uri
import androidx.core.net.toUri

object DeepLinkPattern {
    private val BaseUri = "loryblu:/"
    val RecoveryPasswordPattern = "$BaseUri/password_recovery/"

    fun getPasswordRecoveryUri(): Uri = RecoveryPasswordPattern.toUri()
}