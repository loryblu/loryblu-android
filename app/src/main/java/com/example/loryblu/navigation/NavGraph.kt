package com.example.loryblu.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loryblu.login.LoginScreen
import com.example.loryblu.login.LoginViewModel
import com.example.loryblu.register.guardian.GuardianRegisterScreen
import com.example.loryblu.register.guardian.GuardianRegisterViewModel

@Composable
fun SetupNavGraph(startDestination: String, navController: NavHostController) {
    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        loginRoute(
            navigateToGuardianRegister = {
                navController.popBackStack()
                navController.navigate(Screen.RegisterGuardian.route)
            }
        )
        registerGuardianRoute()
        registerChildRoute()
        createPasswordRoute()
        forgotPasswordRoute()
    }
}

fun NavGraphBuilder.loginRoute(
    navigateToGuardianRegister: () -> Unit,
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
            navigateToGuardianRegister = navigateToGuardianRegister,
        )
    }
}

fun NavGraphBuilder.registerGuardianRoute() {
    composable(route = Screen.RegisterGuardian.route) {
        val viewModel: GuardianRegisterViewModel = viewModel()

        GuardianRegisterScreen(
            viewModel = viewModel,
        )
    }
}

fun NavGraphBuilder.registerChildRoute() {
    composable(route = Screen.RegisterChild.route) {

    }
}

fun NavGraphBuilder.createPasswordRoute() {
    composable(route = Screen.CreatePassword.route) {

    }
}

fun NavGraphBuilder.forgotPasswordRoute() {
    composable(route = Screen.ForgetPassword.route) {

    }
}
