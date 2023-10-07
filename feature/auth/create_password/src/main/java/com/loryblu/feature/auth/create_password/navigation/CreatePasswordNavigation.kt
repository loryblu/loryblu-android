package com.loryblu.feature.auth.create_password.navigation

import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import androidx.compose.runtime.getValue
import androidx.navigation.navDeepLink
import com.loryblu.feature.auth.create_password.CreatePasswordScreen
import com.loryblu.feature.auth.create_password.CreatePasswordViewModel


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
        val expires = backStack.arguments?.getString("expires")
        Log.d("token-createPasswordRoute", "token: $token")
        Log.d("token-createPasswordRoute", "expires in: $expires")

        val viewModel: CreatePasswordViewModel = viewModel()
        val shouldGoToNextScreen by viewModel.shouldGoToNextScreen
        CreatePasswordScreen(
            viewModel = viewModel,
            navigateToLoginScreen = navigateToLoginScreen,
            onResetPasswordButtonClicked = {
                viewModel.verifyAllConditions()
            },
            shouldGoToNextScreen = shouldGoToNextScreen,
        )
    }
}