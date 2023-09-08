package com.example.loryblu.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loryblu.createpassword.CreatePasswordScreen
import com.example.loryblu.createpassword.CreatePasswordViewModel
import com.example.loryblu.forgotpassword.ForgotPasswordScreen
import com.example.loryblu.forgotpassword.ForgotPasswordViewModel
import com.example.loryblu.home.HomeScreen
import com.example.loryblu.login.LoginScreen
import com.example.loryblu.login.LoginViewModel
import com.example.loryblu.register.child.ChildRegisterScreen
import com.example.loryblu.register.child.ChildRegisterViewModel
import com.example.loryblu.register.guardian.GuardianRegisterScreen
import com.example.loryblu.register.guardian.GuardianRegisterViewModel

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
            navigateToHomeScreen = {
                navController.popBackStack(Screen.Login.route, true)
                navController.navigate(Screen.Home.route)
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

        GuardianRegisterScreen(
            viewModel = viewModel,
            navigateToChildRegister = navigateToChildRegister
        )
    }
}

fun NavGraphBuilder.registerChildRoute(
    navigateToHomeScreen: () -> Unit,
) {
    composable(route = Screen.RegisterChild.route) {
        val viewModel: ChildRegisterViewModel = viewModel()
        val shouldGoToNextScreen by viewModel.shouldGoToNextScreen
        ChildRegisterScreen(
            viewModel = viewModel,
            navigateToHomeScreen = navigateToHomeScreen,
            onSignUpButtonClicked = {
                viewModel.loginWithCorrectName()
            },
            shouldGoToNextScreen = shouldGoToNextScreen,
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

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.Home.route) {
        HomeScreen(

        )
    }
}
