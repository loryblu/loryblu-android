package com.odisby.feature.dashboard.ui

import androidx.annotation.StringRes
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.components.LBCardDashboard
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBLightPink
import com.loryblu.core.ui.theme.LBMediumGray
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.core.ui.theme.LBSilverGray
import com.loryblu.core.ui.theme.LBSoftBlue
import com.odisby.data.dashboard.local.getAllDashboardItems
import com.odisby.feature.dashboard.R
import com.odisby.feature.dashboard.model.UsesData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun DashboardScreen(
    usesData: UsesData,
    navigateToLogbook: () -> Unit,
) {
    var menuIsOpen by rememberSaveable { mutableStateOf(false) }

    val dashboardItems = getAllDashboardItems()

    Column(modifier = Modifier.fillMaxSize()) {
        MenuContent(
            isOpen = menuIsOpen,
            childFullName = usesData.childFullName,
            parentFullName = usesData.parentFullName,
            onClose = { menuIsOpen = false }
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
    }
}

@Composable
fun AppBar(childFirstName: String, onMenuClick: () -> Unit) {
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
                text = stringResource(id = R.string.hello, childFirstName)
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

@ExperimentalMaterial3Api
@Composable
fun MenuContent(
    isOpen: Boolean,
    childFullName: String,
    parentFullName: String,
    onClose: () -> Unit
) {
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
        val scrollState = rememberScrollState()
        ModalBottomSheet(
            sheetState = state,
            onDismissRequest = onCloseClick,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                MenuHeader(onCloseClick = onCloseClick)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    MenuSection(R.string.profile) {
                        MenuItem(
                            smallText = stringResource(id = R.string.child_name),
                            mediumText = childFullName,
                        )
                        MenuItem(
                            smallText = stringResource(id = R.string.parent_name),
                            mediumText = parentFullName,
                        )
                    }
                    MenuSection(R.string.configurations) {
                        MenuItem(
                            smallText = stringResource(id = R.string.access_control),
                            mediumText = stringResource(id = R.string.security),
                            isReverseTextOrder = true,
                        )
                        MenuItem(
                            mediumText = stringResource(id = R.string.faq),
                        )
                        MenuItem(
                            mediumText = stringResource(id = R.string.privacy_term),
                        )
                        MenuItem(
                            mediumText = stringResource(id = R.string.exit_app),
                            backgroundColor = LBLightPink,
                            paddingTop = 24.dp,
                            hasArrowRight = false,
                        )
                    }
                }
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

@Composable
fun MenuSection(@StringRes titleId: Int, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(top = 24.dp, bottom = 0.dp)) {
        Text(
            text = stringResource(id = titleId),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = LBMediumGray,
        )
        Column {
            content.invoke()
        }
    }
}

@Composable
fun MenuItem(
    smallText: String = "",
    mediumText: String,
    isReverseTextOrder: Boolean = false,
    backgroundColor: Color = LBSoftBlue,
    paddingTop: Dp = 16.dp,
    hasArrowRight: Boolean = true,
) {
    Spacer(modifier = Modifier.padding(top = paddingTop))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .border(1.dp, LBLightGray, shape = RoundedCornerShape(12.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(45.dp), painter =
                painterResource(id = R.drawable.logo_home),
                contentDescription = ""
            )
            Column(
                Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (isReverseTextOrder) {
                    MenuMediumText(mediumText)
                    MenuSmallText(smallText)
                } else {
                    MenuSmallText(smallText)
                    MenuMediumText(mediumText)
                }
            }

        }
        if (hasArrowRight) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = LBSilverGray,
            )
        }
    }
}


@Composable
fun MenuSmallText(text: String, modifier: Modifier = Modifier) {
    if (text.isNotEmpty()) {
        Text(
            text = text,
            color = Color.Gray,
            style = MaterialTheme.typography.labelLarge,
            modifier = modifier,
        )
    }
}

@Composable
fun MenuMediumText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Medium,
        color = LBShadowGray,
        modifier = modifier
    )
}

@Preview
@Composable
fun MenuItemPreview() {
    MenuItem("Nome da criança", "Maria da Silva")

}
