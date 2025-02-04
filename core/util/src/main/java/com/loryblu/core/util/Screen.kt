package com.loryblu.core.util

import kotlinx.serialization.Serializable

object Screen {

    object Authentication {
        @Serializable
        object Login

        @Serializable
        object RegisterGuardian

        @Serializable
        object RegisterChild

        @Serializable
        data class CreatePassword(val token: String? = null, val expires: String? = null)

        @Serializable
        object ForgotPassword

        @Serializable
        object RegistrationConfirmed
    }


    @Serializable
    object Dashboard

    @Serializable
    object LogbookGraph

    @Serializable
    object CreateTaskGraph

    object Logbook {
        @Serializable
        data class Home(val updateAnimation: Boolean = false, val successAdd: Boolean = false)

        @Serializable
        object CreateCategoryScreen

        @Serializable
        object CreateTaskScreen

        @Serializable
        object CreateSummaryScreen

        @Serializable
        object CreateShiftScreen

        @Serializable
        object EditCategoryScreen

        @Serializable
        object EditTaskScreen

        @Serializable
        data class EditTaskSummaryScreen(val taskId: Int)

        @Serializable
        object EditionConfirmedScreen

    }

    object Menu {
        @Serializable
        data class WebViewScreen(val url: String, val title: String)

        @Serializable
        object ChildrenProfileScreen

        @Serializable
        object ParentProfileScreen

        @Serializable
        object ResetPasswordScreen
    }

}
