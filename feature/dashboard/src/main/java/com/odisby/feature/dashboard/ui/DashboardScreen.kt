package com.odisby.feature.dashboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.components.LBCardDashboard
import com.odisby.data.dashboard.local.getAllDashboardItems
import com.odisby.feature.dashboard.model.UsesData

@ExperimentalMaterial3Api
@Composable
fun DashboardScreen(
    usesData: UsesData,
    navigateToLogbook: () -> Unit,
    navigateToLogin: () -> Unit
) {
    var menuIsOpen by rememberSaveable { mutableStateOf(false) }
    var showExitDialog by rememberSaveable { mutableStateOf(false) }
    val dashboardItems = getAllDashboardItems()

    Column(modifier = Modifier.fillMaxSize()) {
        MenuContent(
            menuIsOpen = menuIsOpen,
            childFullName = usesData.childFullName,
            parentFullName = usesData.parentFullName,
            onCloseMenu = { menuIsOpen = false },
            onExitApp = { showExitDialog = true },
        )
        AppBar(
            childFirstName = usesData.childFirstName,
            onMenuClick = { menuIsOpen = true },
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn {
                items(dashboardItems) { item ->
                    LBCardDashboard(
                        card = item,
                        modifier = Modifier
                            .weight(0.3f)
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(top = 12.dp, bottom = 12.dp, start = 24.dp, end = 24.dp),
                        onclick = { navigateToLogbook() }
                    )
                }
            }
        }
        if (showExitDialog) {
            ExitAppDialog(
                onCancel = { showExitDialog = false },
                onConfirm = navigateToLogin,
            )
        }
    }
}
