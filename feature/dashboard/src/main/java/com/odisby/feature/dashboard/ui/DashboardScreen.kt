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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.components.LBCardDashboard
import com.loryblu.core.ui.theme.LBMediumGray
import com.loryblu.core.ui.theme.LBShadowGray
import com.odisby.data.dashboard.local.getAllDashboardItems
import com.odisby.feature.dashboard.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun DashboardScreen(
    navigateToLogbook: () -> Unit,
    childName: String = "",
) {
    var menuIsOpen by rememberSaveable { mutableStateOf(false) }

    val dashboardItems = getAllDashboardItems()

    Column(modifier = Modifier.fillMaxSize()) {
        MenuContent(isOpen = menuIsOpen, onClose = { menuIsOpen = false })
        AppBar(childName = childName, onMenuClick = { menuIsOpen = true })
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
                contentDescription = stringResource(id = R.string.logo_home),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(62.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                fontSize = 24.sp,
                text = stringResource(id = R.string.hello, childName)
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
            contentDescription = stringResource(id = R.string.menu),
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
fun MenuContent(isOpen: Boolean, onClose: () -> Unit) {
    val scope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(isOpen) {
        if (isOpen) state.show() 
    }

    val onCloseClick: () -> Unit = {
        scope.launch {
            state.hide()
            delay(300) // to await close animation
            onClose.invoke()
        }
    }

    if (isOpen) {
        ModalBottomSheet(
            sheetState = state,
            onDismissRequest = onCloseClick,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MenuHeader(onCloseClick = onCloseClick)
            }
        }
    }
}

@Composable
fun MenuHeader(onCloseClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.menu),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = LBShadowGray,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(
            Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_menu),
            tint = LBMediumGray,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterEnd)
                .clickable { onCloseClick.invoke() },
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun MenuContentPreview() {
    MenuContent(isOpen = true, onClose = {})
}
