package com.loryblu.feature.logbook.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import com.loryblu.feature.logbook.ui.LogbookScreen

fun NavGraphBuilder.logbookRoute(
    onBackButtonClicked: () -> Unit
) {
    composable(route = Screen.Logbook.route) {
        LogbookScreen(
            onBackButtonClicked = onBackButtonClicked
        )
    }
}