package com.loryblu.feature.auth.register.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.feature.auth.register.RegistrationConfirmedScreen
import com.loryblu.feature.auth.register.child.ChildRegisterScreen
import com.loryblu.feature.auth.register.child.ChildRegisterViewModel
import com.loryblu.feature.auth.register.guardian.GuardianRegisterScreen
import com.loryblu.feature.auth.register.guardian.GuardianRegisterViewModel
import com.loryblu.core.util.Screen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.registerGuardianRoute(
    navigateToChildRegister: () -> Unit,
) {
    composable(route = Screen.RegisterGuardian.route) {
        val viewModel: GuardianRegisterViewModel = viewModel()
        val shouldGoToNextScreen by viewModel.shouldGoToNextScreen

        GuardianRegisterScreen(
            viewModel = viewModel,
            onNextButtonClicked = {
                viewModel.verifyAllConditions()
            },
            navigateToChildRegister = navigateToChildRegister,
            shouldGoToNextScreen = shouldGoToNextScreen,
        )
    }
}

fun NavGraphBuilder.registerChildRoute(
    navigateToConfirmationScreen: () -> Unit,
) {
    composable(route = Screen.RegisterChild.route) {
        val viewModel: ChildRegisterViewModel = koinViewModel()
        val shouldGoToNextScreen by viewModel.shouldGoToNextScreen
        val intentForPrivacyPolicy = Intent(Intent.ACTION_VIEW)
        intentForPrivacyPolicy.setData(Uri.parse("https://online.fliphtml5.com/ibqqn/mtvs/#p=1"))

        ChildRegisterScreen(
            viewModel = viewModel,
            navigateToConfirmationScreen = navigateToConfirmationScreen,
            onSignUpButtonClicked = {
                viewModel.verifyAllConditions()
            },
            shouldGoToNextScreen = shouldGoToNextScreen,
            intentForPrivacy = intentForPrivacyPolicy,
        )
    }
}

fun NavGraphBuilder.registrationConfirmedRoute(
    navigateToHomeScreen: () -> Unit,
) {
    composable(route = Screen.RegistrationConfirmed.route) {
        RegistrationConfirmedScreen(
            navigateToHomeScreen = navigateToHomeScreen,
            shouldGoToNextScreen = true //sempre será true
        )
    }
}