package com.loryblu.feature.auth.login.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.feature.auth.login.LoginScreen
import com.loryblu.feature.auth.login.LoginViewModel
import com.loryblu.core.util.Screen

fun NavGraphBuilder.loginRoute(
    navigateToHomeScreen: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToRegisterNow: () -> Unit,
) {
    composable(route = Screen.Login.route) {
        val viewModel: LoginViewModel = viewModel()
        val authenticated by viewModel.authenticated

        LoginScreen(
            viewModel = viewModel,
            authenticated = authenticated,
            onLoginButtonClicked = {
                viewModel.loginWithEmailAndPassword()
            },
            navigateToHomeScreen = navigateToHomeScreen,
            navigateToRegisterNow = navigateToRegisterNow,
            navigateToForgotPassword = navigateToForgotPassword,
        )
    }
}