package com.loryblu.feature.auth.create_password.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import androidx.compose.runtime.getValue
import com.loryblu.feature.auth.create_password.CreatePasswordScreen
import com.loryblu.feature.auth.create_password.CreatePasswordViewModel


fun NavGraphBuilder.createPasswordRoute(
    navigateToLoginScreen: () -> Unit,
) {
    composable(route = Screen.CreatePassword.route) {
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