package com.loryblu.feature.auth.create_password.navigation

import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.loryblu.core.util.Screen
import com.loryblu.feature.auth.create_password.CreatePasswordScreen
import com.loryblu.feature.auth.create_password.CreatePasswordViewModel
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.createPasswordRoute(
    navigateToLoginScreen: () -> Unit,
) {

    composable(
        route = Screen.CreatePassword.route,
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "loryblu://password_recovery/?r_token%3D{token}%26expires_in%3D{expires}"
            }
        ),
    ) { backStack ->
        val token = backStack.arguments?.getString("token")

        val viewModel: CreatePasswordViewModel = koinViewModel()
        val shouldGoToNextScreen by viewModel.shouldGoToNextScreen
        if (token != null) {
            CreatePasswordScreen(
                viewModel = viewModel,
                navigateToLoginScreen = navigateToLoginScreen,
                onResetPasswordButtonClicked = {
                    viewModel.verifyAllConditions(token)
                },
                shouldGoToNextScreen = shouldGoToNextScreen
            )
        }
    }
}