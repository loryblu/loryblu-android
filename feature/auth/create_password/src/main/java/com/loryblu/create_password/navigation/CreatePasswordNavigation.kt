package com.loryblu.create_password.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.util.Screen
import androidx.compose.runtime.getValue


fun NavGraphBuilder.createPasswordRoute(
    navigateToLoginScreen: () -> Unit,
) {
    composable(route = Screen.CreatePassword.route) {
        val viewModel: com.loryblu.create_password.CreatePasswordViewModel = viewModel()
        val shouldGoToNextScreen by viewModel.shouldGoToNextScreen
        com.loryblu.create_password.CreatePasswordScreen(
            viewModel = viewModel,
            navigateToLoginScreen = navigateToLoginScreen,
            onResetPasswordButtonClicked = {
                viewModel.verifyAllConditions()
            },
            shouldGoToNextScreen = shouldGoToNextScreen,
        )
    }
}