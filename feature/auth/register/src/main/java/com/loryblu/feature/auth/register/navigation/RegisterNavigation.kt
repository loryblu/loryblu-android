package com.loryblu.feature.auth.register.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import com.loryblu.feature.auth.register.presentation.RegistrationConfirmedScreen
import com.loryblu.feature.auth.register.RegisterViewModel
import com.loryblu.feature.auth.register.presentation.ChildRegisterScreen
import com.loryblu.feature.auth.register.presentation.GuardianRegisterScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

fun NavGraphBuilder.registerGuardianRoute(
    navigateToChildRegister: () -> Unit,
) {
    composable(route = Screen.RegisterGuardian.route) {
        val viewModel: RegisterViewModel = koinViewModel(qualifier = named("registerScope"))
        val shouldGoToNextScreen by viewModel.shouldGoToNextScreenGuardian

        GuardianRegisterScreen(
            onNextButtonClicked = { guardian ->
                viewModel.saveGuardian(guardian)
            },
            navigateToChildRegister = navigateToChildRegister,
            shouldGoToNextScreen = shouldGoToNextScreen,
            nameStateValidation = {
                viewModel.nameState(it)
            },
            emailStateValidation = {
                viewModel.emailState(it)
            },
            confirmPasswordStateValidation = { password, confirmPassword ->
                viewModel.confirmPasswordState(password, confirmPassword)
            },
            passwordStateValidation = {
                viewModel.passwordState(it)
            }
        )
    }
}

fun NavGraphBuilder.registerChildRoute(
    navigateToConfirmationScreen: () -> Unit,
) {
    composable(route = Screen.RegisterChild.route) {
        val viewModel: RegisterViewModel =  koinViewModel(qualifier = named("registerScope"))
        val shouldGoToNextScreen by viewModel.shouldGoToNextScreenChildren
        val intentForPrivacyPolicy = Intent(Intent.ACTION_VIEW)
        val apiErrorMessage by viewModel.apiErrorMessage.collectAsState()
        intentForPrivacyPolicy.setData(Uri.parse("https://online.fliphtml5.com/ibqqn/mtvs/#p=1"))

        ChildRegisterScreen(
            navigateToConfirmationScreen = navigateToConfirmationScreen,
            onSignUpButtonClicked = { children ->
                viewModel.verifyAllConditions(children)
            },
            shouldGoToNextScreen = shouldGoToNextScreen,
            intentForPrivacy = intentForPrivacyPolicy,
            apiErrorMessage = apiErrorMessage,
            birthdayStateValidation =  {
                viewModel.birthdayState(it)
            },
            genderStateValidation = {
                viewModel.genderState(it)
            },
            nameStateValidation = {
                viewModel.nameState(it)
            }
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