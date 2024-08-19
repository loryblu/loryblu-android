package com.odisby.feature.dashboard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.components.LBCardDashboard
import com.odisby.data.dashboard.local.getAllDashboardItems
import com.odisby.feature.dashboard.R

@ExperimentalMaterial3Api
@Composable
fun DashboardScreen(
    navigateToLogbook: () -> Unit,
    childName: String = "",
) {
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

    val dashboardItems = getAllDashboardItems()

    Column(modifier = Modifier.fillMaxSize()) {
        MenuContent(
            menuIsOpen = openBottomSheet,
            onClose = { openBottomSheet = false }
        )
        AppBar(childName = childName, onMenuClick = { openBottomSheet = true })
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
    }
}

@Composable
fun AppBar(childName: String, onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 26.dp, bottom = 32.dp, end = 26.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo_home),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(62.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                fontSize = 24.sp,
                text = "Olá, $childName"
            )
        }
        MenuIcon(onClick = onMenuClick)
    }
}

@Composable
fun MenuIcon(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(20))
            .border(
                BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(20)
            )
            .clickable { onClick.invoke() }
            .shadow(1.dp, shape = RoundedCornerShape(20))
            .background(Color.White)
    ) {
        Icon(
            Icons.Filled.Menu,
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier.size(22.dp)
        )
    }

}

@Preview
@Composable
fun AppBarPreview() {
    AppBar(childName = "Sara") {}
}

@ExperimentalMaterial3Api
@Composable
fun MenuContent(menuIsOpen: Boolean, onClose: () -> Unit) {
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (menuIsOpen) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = onClose,
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

            }
        }
    }
}
