package com.loryblu.feature.auth.forgot_password.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import androidx.compose.runtime.getValue
import com.loryblu.feature.auth.forgot_password.ForgotPasswordScreen
import com.loryblu.feature.auth.forgot_password.ForgotPasswordViewModel

fun NavGraphBuilder.forgotPasswordRoute(
    navigateToCreatePassword: () -> Unit,
) {
    composable(route = Screen.ForgetPassword.route) {
        val viewModel: ForgotPasswordViewModel = viewModel()
        val authenticated by viewModel.authenticated
        val sendEmailSuccess by viewModel.sendEmailSuccess

        ForgotPasswordScreen(
            viewModel = viewModel,
            authenticated = authenticated,
            sendEmailSuccess = sendEmailSuccess,
            navigateToCreatePasswordScreen = navigateToCreatePassword,
        )
    }
}