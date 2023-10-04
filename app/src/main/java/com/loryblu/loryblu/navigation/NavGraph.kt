package com.loryblu.loryblu.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.loryblu.loryblu.createpassword.CreatePasswordScreen
import com.loryblu.loryblu.createpassword.CreatePasswordViewModel
import com.loryblu.loryblu.forgotpassword.ForgotPasswordScreen
import com.loryblu.loryblu.forgotpassword.ForgotPasswordViewModel
import com.loryblu.loryblu.register.registration.RegistrationConfirmedScreen
import com.loryblu.loryblu.home.HomeScreen
import com.loryblu.loryblu.login.LoginScreen
import com.loryblu.loryblu.login.LoginViewModel
import com.loryblu.loryblu.register.child.ChildRegisterScreen
import com.loryblu.loryblu.register.child.ChildRegisterViewModel
import com.loryblu.loryblu.register.guardian.GuardianRegisterScreen
import com.loryblu.loryblu.register.guardian.GuardianRegisterViewModel

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

fun NavGraphBuilder.loginRoute(
    navigateToHomeScreen: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    navigateToRegisterNow: () -> Unit,
) {
    composable(route = Screen.Login.route) {
        val viewModel: LoginViewModel = viewModel()
        val authenticated by viewModel.authenticated

        LoginScreen(
            viewModel = viewModel,
            authenticated = authenticated,
            onLoginButtonClicked = {
                viewModel.loginWithEmailAndPassword()
            },
            navigateToHomeScreen = navigateToHomeScreen,
            navigateToRegisterNow = navigateToRegisterNow,
            navigateToForgotPassword = navigateToForgotPassword,
        )
    }
}

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
        val viewModel: ChildRegisterViewModel = viewModel()
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

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.Home.route) {
        HomeScreen(

        )
    }
}
