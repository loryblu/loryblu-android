package com.loryblu.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loryblu.home.HomeScreen
import com.loryblu.util.Screen

fun NavGraphBuilder.homeRoute() {
    composable(route = Screen.Home.route) {
        HomeScreen(

        )
    }
}
