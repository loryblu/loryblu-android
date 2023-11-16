package com.loryblu.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.core.util.Screen
import com.loryblu.feature.home.HomeLogbookScreen

fun NavGraphBuilder.homeLogbookRoute(
) {
    composable(route = Screen.HomeLogbook.route) {
        HomeLogbookScreen(
        )
    }
}