package com.example.loryblu

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

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
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = LorybluApp.valueOf(
        backStackEntry?.destination?.route ?: CupcakeScreen.Start.name
    )
}