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
                uriPattern = "loryblu://password_recovery/{token}"
            }
        ),
    ) { backStack ->
        val token = backStack.arguments?.getString("token")
        Log.d("token", "createPasswordRoute: $token")
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