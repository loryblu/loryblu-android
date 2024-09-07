package com.odisby.feature.dashboard.ui

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.Surface
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
