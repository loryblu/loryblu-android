package com.loryblu.loryblu.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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
import com.loryblu.loryblu.R
import com.odisby.feature.dashboard.navigation.dashboardRoute

@Composable
fun SetupNavGraph(startDestination: String, navController: NavHostController) {
    val context = LocalContext.current
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
            navigateToHome = {
                navController.popBackStack(Screen.RegistrationConfirmed.route, true)
                navController.navigate(Screen.Login.route)
            }
        )
        dashboardRoute(
            navigateToLogbook = {
                navController.navigate(Screen.Logbook.route)
            },
            navigateToLogin = {
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            },
            navigateToFaq = {
                navController.navigate(
                    Screen.WebViewScreen.editRoute(
                        url = " https://drive.google.com/file/d/1AZMx5-b1mLGPs041Zok6X2blpjIkZ2vs/view",
                        title = context.getString(R.string.faq_title),
                    )
                )
            },
            navigateToTerms = {
                navController.navigate(
                    Screen.WebViewScreen.editRoute(
                        url = "https://drive.google.com/file/d/1A1WIDpEiSVI9Ep5oA9YMSA-JWC2O8q4M/view",
                        title = context.getString(R.string.terms_title),
                    )
                )
            },
            navigateToChildrenProfile = {
                navController.navigate(Screen.ChildrenProfileScreen.route)
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
