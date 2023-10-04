package com.loryblu.loryblu.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loryblu.login.navigation.loginRoute
import com.loryblu.loryblu.createpassword.CreatePasswordScreen
import com.loryblu.loryblu.createpassword.CreatePasswordViewModel
import com.loryblu.loryblu.forgotpassword.ForgotPasswordScreen
import com.loryblu.loryblu.forgotpassword.ForgotPasswordViewModel
import com.loryblu.loryblu.home.HomeScreen
import com.loryblu.register.navigation.registerChildRoute
import com.loryblu.register.navigation.registerGuardianRoute
import com.loryblu.register.navigation.registrationConfirmedRoute
import com.loryblu.util.Screen

@Composable
fun SetupNavGraph(startDestination: String, navController: NavHostController) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        loginRoute(
            navigateToHomeScreen = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            },
            navigateToForgotPassword = {
                navController.navigate(Screen.ForgetPassword.route)
            },
            navigateToRegisterNow = {
                navController.navigate(Screen.RegisterGuardian.route)
            }
        )
        registerGuardianRoute(
            navigateToChildRegister = {
                navController.popBackStack()
                navController.navigate(Screen.RegisterChild.route)
            }
        )
        registerChildRoute(
            navigateToConfirmationScreen = {
                navController.popBackStack(Screen.Login.route, true)
                navController.navigate(Screen.RegistrationConfirmed.route)
            }
        )
        createPasswordRoute(
            navigateToLoginScreen = {
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            }
        )
        forgotPasswordRoute(
            navigateToCreatePassword = {
                navController.popBackStack()
                navController.navigate(Screen.CreatePassword.route)
            }
        )
        registrationConfirmedRoute(
            navigateToHomeScreen = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        )
        homeRoute()
    }
}

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

fun NavGraphBuilder.forgotPasswordRoute(
    navigateToCreatePassword: () -> Unit,
) {
    composable(route = Screen.ForgetPassword.route) {
        val viewModel: ForgotPasswordViewModel = viewModel()
        val authenticated by viewModel.authenticated
        val sendEmailSuccess by viewModel.sendEmailSuccess

        ForgotPasswordScreen(
            viewModel = viewModel,
            authenticated = authenticated,
            sendEmailSuccess = sendEmailSuccess,
            navigateToCreatePasswordScreen = navigateToCreatePassword,
        )
    }
}

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.Home.route) {
        HomeScreen(

        )
    }
}
