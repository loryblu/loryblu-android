package com.loryblu.util

sealed class Screen(val route: String) {
    object Login: Screen(route = "login_screen")
    object RegisterGuardian: Screen(route = "register_guardian_screen")
    object RegisterChild: Screen(route = "register_child_screen")
    object CreatePassword: Screen(route = "create_password_screen")
    object ForgetPassword: Screen(route = "forget_password_screen")
    object Home: Screen(route = "home_screen")
    object RegistrationConfirmed: Screen(route = "registration_confirmed_screen")
}
