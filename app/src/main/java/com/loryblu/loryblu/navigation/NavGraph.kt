package com.loryblu.loryblu.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.loryblu.core.util.Screen
import com.loryblu.feature.auth.create_password.navigation.createPasswordRoute
import com.loryblu.feature.auth.forgot_password.navigation.forgotPasswordRoute
import com.loryblu.feature.auth.login.navigation.loginRoute
import com.loryblu.feature.auth.register.navigation.registerChildRoute
import com.loryblu.feature.auth.register.navigation.registerGuardianRoute
import com.loryblu.feature.auth.register.navigation.registrationConfirmedRoute
import com.loryblu.feature.logbook.navigation.logbookNavigation
import com.odisby.feature.dashboard.navigation.dashboardRoute

@Composable
fun SetupNavGraph(startDestination: String, navController: NavHostController) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        loginRoute(
            navigateToDashboard = {
                navController.popBackStack()
                navController.navigate(Screen.Dashboard.route)
            },
            navigateToForgotPassword = {
                navController.navigate(Screen.ForgotPassword.route)
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
            },
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
            navigateToDashboard = {
                navController.popBackStack(Screen.RegistrationConfirmed.route, true)
                navController.navigate(Screen.Dashboard.route)
            }
        )
        dashboardRoute(
            navigateToLogbook = {
                navController.navigate(Screen.Logbook.route)
            }
        )
        logbookNavigation(
            navController = navController,
            onBackButtonClicked = {
                navController.popBackStack()
                navController.navigate(Screen.Dashboard.route)
            },
        )

    }
}
