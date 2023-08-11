package com.example.loryblu

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.loryblu.createpassword.CreatePasswordScreen
import com.example.loryblu.createpassword.CreatePasswordViewModel
import com.example.loryblu.forgotpassword.ForgotPasswordScreen
import com.example.loryblu.forgotpassword.ForgotPasswordViewModel
import com.example.loryblu.login.LoginScreen
import com.example.loryblu.login.LoginViewModel
import com.example.loryblu.register.GuardianRegisterScreen
import com.example.loryblu.register.GuardianRegisterViewModel

enum class LorybluApp(@StringRes val title: Int) {
    CREATE_PASSWORD(R.string.create_a_new_password),
    FORGOT_PASSWORD(R.string.forgot_password),
    LOGIN(R.string.login),
    // register guardian
    REGISTER_GUARDIAN(R.string.guardian_registration),
    // register kid
    REGISTER_KID(R.string.kid_register)
}
@Composable
fun LorybluApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    // Get the current back stack entry (pegar a stack atual do aplicativo)
    val backStackEntry by navController.currentBackStackEntryAsState()
    // pegar o nome da tela atual
    val currentScreen = LorybluApp.valueOf(
        backStackEntry?.destination?.route ?: LorybluApp.LOGIN.name
    )
    
    NavHost(
        navController = navController,
        startDestination = LorybluApp.LOGIN.name
    ) {
        composable(route = LorybluApp.LOGIN.name) {
            LoginScreen(viewModel = LoginViewModel())
        }

        composable(route = LorybluApp.REGISTER_GUARDIAN.name) {
            GuardianRegisterScreen(viewModel = GuardianRegisterViewModel())
        }

        // TODO implement this navigation screen
        composable(route = LorybluApp.REGISTER_KID.name) {

        }

        composable(route = LorybluApp.FORGOT_PASSWORD.name) {
            ForgotPasswordScreen(viewModel = ForgotPasswordViewModel())
        }

        composable(route = LorybluApp.CREATE_PASSWORD.name) {
            CreatePasswordScreen(viewModel = CreatePasswordViewModel())
        }
    }

}