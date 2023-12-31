package com.loryblu.feature.auth.forgot_password.navigation

import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import com.loryblu.feature.auth.forgot_password.ForgotPasswordScreen
import com.loryblu.feature.auth.forgot_password.ForgotPasswordViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.forgotPasswordRoute(
    navigateToCreatePassword: () -> Unit,
) {
    composable(
        route = Screen.ForgotPassword.route,
    ) {
        val viewModel: ForgotPasswordViewModel = koinViewModel()
        val authenticated by viewModel.authenticated
        ForgotPasswordScreen(
            viewModel = viewModel,
            authenticated = authenticated,
            navigateToCreatePasswordScreen = navigateToCreatePassword,
        )
    }
}
