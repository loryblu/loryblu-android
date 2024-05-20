package com.loryblu.core.util

sealed class Screen(val route: String) {
    // Authentication
    data object Login: Screen(route = "login_screen")
    data object RegisterGuardian: Screen(route = "register_guardian_screen")
    data object RegisterChild: Screen(route = "register_child_screen")
    data object CreatePassword: Screen(route = "password_recovery/?r_token%3D{token}%26expires_in%3D{expires}")
    data object ForgotPassword: Screen(route = "forgot_password_screen")
    data object RegistrationConfirmed: Screen(route = "registration_confirmed_screen")

    // Dashboard
    data object Dashboard: Screen(route = "dashboard_screen")

    // Logbook
    data object Logbook: Screen(route = "logbook_screen?update={UPDATE_ANIMATION}&success={SUCCESS_ADD}") {
        fun withUpdateToast(success: Boolean = true) = "logbook_screen?update=true&success=$success"
    }

    data object CategoryScreen: Screen(route = "category_screen")
    data object EditCategoryScreen: Screen(route = "edit_category_screen")
    data object TaskScreen: Screen(route = "task_screen")
    data object EditTaskScreen: Screen(route = "edit_task_screen")
    data object ShiftScreen: Screen(route = "shift_screen")
    data object SummaryScreen: Screen(route = "summary_screen")
    data object EditTaskSummaryScreen: Screen(route = "edit_task_summary_screen/{TASK_ID}") {
        fun editRoute(taskId: Int) = "edit_task_summary_screen/$taskId"
    }
    data object EditionConfirmedScreen: Screen(route = "edition_confirmed_screen")
}
