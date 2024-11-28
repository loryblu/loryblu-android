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
fun SetupNavGraph(startDestination: Any, navController: NavHostController) {
    val context = LocalContext.current
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        loginRoute(
            navigateToDashboard = {
                navController.popBackStack()
                navController.navigate(Screen.Dashboard)
            },
            navigateToForgotPassword = {
                navController.navigate(Screen.Authentication.ForgotPassword)
            },
            navigateToRegisterNow = {
                navController.navigate(Screen.Authentication.RegisterGuardian)
            }
        )
        registerGuardianRoute(
            navigateToChildRegister = {
                navController.popBackStack()
                navController.navigate(Screen.Authentication.RegisterChild)
            }
        )
        registerChildRoute(
            navigateToConfirmationScreen = {
                navController.popBackStack(Screen.Authentication.Login, true)
                navController.navigate(Screen.Authentication.RegistrationConfirmed)
            },
        )
        createPasswordRoute(
            navigateToLoginScreen = {
                navController.popBackStack()
                navController.navigate(Screen.Authentication.Login)
            }
        )
        forgotPasswordRoute(
            navigateToCreatePassword = {
                navController.popBackStack()
                navController.navigate(Screen.Authentication.CreatePassword())
            }
        )
        registrationConfirmedRoute(
            navigateToHome = {
                navController.popBackStack(Screen.Authentication.RegistrationConfirmed, true)
                navController.navigate(Screen.Authentication.Login)
            }
        )
        dashboardRoute(
            navigateToLogbook = {
                navController.navigate(Screen.Logbook.Home())
            },
            navigateToLogin = {
                navController.popBackStack()
                navController.navigate(Screen.Authentication.Login)
            },
            navigateToFaq = {
                navController.navigate(
                    Screen.Menu.WebViewScreen(
                        url = "https://drive.google.com/file/d/1AZMx5-b1mLGPs041Zok6X2blpjIkZ2vs/view",
                        title = context.getString(R.string.faq_title)
                    )
                )
            },
            navigateToTerms = {
                navController.navigate(
                    Screen.Menu.WebViewScreen(
                        url = "https://drive.google.com/file/d/1A1WIDpEiSVI9Ep5oA9YMSA-JWC2O8q4M/view",
                        title = context.getString(R.string.terms_title),
                    )
                )
            },
            navigateToChildrenProfile = {
                navController.navigate(Screen.Menu.ChildrenProfileScreen)
            }
        )
        logbookNavigation(
            navController = navController,
            onBackButtonClicked = {
                navController.popBackStack(Screen.Dashboard, false)
            },
        )

    }
}
