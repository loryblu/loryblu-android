package com.odisby.feature.dashboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.components.LBCardDashboard
import com.odisby.data.dashboard.local.getAllDashboardItems
import com.odisby.feature.dashboard.R

@Composable
fun DashboardScreen(
    navigateToLogbook: () -> Unit
) {

    val dashboardItems = getAllDashboardItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(top = 12.dp, start = 26.dp, bottom = 32.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
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
                text = "Olá, Bia" //this resource will come from api
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
            ) {
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
