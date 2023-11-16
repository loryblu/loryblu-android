package com.loryblu.core.util

sealed class Screen(val route: String) {
    data object Login: Screen(route = "login_screen")
    data object RegisterGuardian: Screen(route = "register_guardian_screen")
    data object RegisterChild: Screen(route = "register_child_screen")
    data object CreatePassword: Screen(route = "password_recovery/?r_token%3D{token}%26expires_in%3D{expires}")
    data object ForgotPassword: Screen(route = "forgot_password_screen")
    data object Home: Screen(route = "home_screen")
    data object HomeLogbook: Screen(route = "home_logbook_screen")
    data object RegistrationConfirmed: Screen(route = "registration_confirmed_screen")
}
