package com.loryblu.feature.auth.register.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import com.loryblu.feature.auth.register.RegistrationConfirmedScreen
import com.loryblu.feature.auth.register.child.ChildRegisterScreen
import com.loryblu.feature.auth.register.child.ChildRegisterViewModel
import com.loryblu.feature.auth.register.guardian.GuardianRegisterScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

fun NavGraphBuilder.registerGuardianRoute(
    navigateToChildRegister: () -> Unit,
) {
    composable(route = Screen.RegisterGuardian.route) {
        val viewModel: ChildRegisterViewModel = koinViewModel(qualifier = named("registerScope"))
        val shouldGoToNextScreen by viewModel.shouldGoToNextScreenGuardian

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
        val viewModel: ChildRegisterViewModel =  koinViewModel(qualifier = named("registerScope"))
        val shouldGoToNextScreen by viewModel.shouldGoToNextScreenChildren
        val intentForPrivacyPolicy = Intent(Intent.ACTION_VIEW)
        val apiErrorMessage by viewModel.apiErrorMessage
        intentForPrivacyPolicy.setData(Uri.parse("https://online.fliphtml5.com/ibqqn/mtvs/#p=1"))

        ChildRegisterScreen(
            viewModel = viewModel,
            navigateToConfirmationScreen = navigateToConfirmationScreen,
            onSignUpButtonClicked = { children ->
                viewModel.verifyAllConditions(children)
            },
            shouldGoToNextScreen = shouldGoToNextScreen,
            intentForPrivacy = intentForPrivacyPolicy,
            apiErrorMessage = apiErrorMessage,
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