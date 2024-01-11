package com.loryblu.feature.auth.login.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.feature.auth.login.LoginScreen
import com.loryblu.feature.auth.login.LoginViewModel
import com.loryblu.core.util.Screen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.loginRoute(
    navigateToDashboard: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToRegisterNow: () -> Unit,
) {
    composable(route = Screen.Login.route) {
        val viewModel: LoginViewModel = koinViewModel()
        val authenticated by viewModel.authenticated.collectAsState()
        val signInResult by viewModel.signInResult.collectAsState()

        LoginScreen(
            authenticated = authenticated,
            onLoginButtonClicked = {
                viewModel.loginWithEmailAndPassword(it)
            },
            navigateToHomeScreen = navigateToDashboard,
            navigateToRegisterNow = navigateToRegisterNow,
            navigateToForgotPassword = navigateToForgotPassword,
            emailStateValidation = { viewModel.emailState(it) },
            passwordStateValidation = { viewModel.passwordState(it) },
            signInResult = signInResult,
            rememberLogin = viewModel::rememberLogin
        )
    }
}