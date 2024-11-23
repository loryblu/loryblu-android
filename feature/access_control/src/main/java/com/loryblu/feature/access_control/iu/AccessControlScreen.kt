package com.loryblu.feature.access_control.iu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.theme.inter
import com.loryblu.feature.access_control.R
import com.loryblu.feature.access_control.iu.components.AccessItemCard
import com.loryblu.feature.access_control.model.getAllAccessItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccessControlScreen(
    onBackButtonClicked: () -> Unit,
    onCloseButtonClicked: () -> Unit,
) {
    val accessItems = getAllAccessItems()
    var isChecked = false

    Scaffold (
        modifier = Modifier.padding(16.dp),
        topBar = {
            LBTopAppBar(
                title = stringResource(R.string.access_control_title),
                onBackClicked = { onBackButtonClicked() },
                onCloseClicked = { onCloseButtonClicked() },
                showCloseButton = true
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(
                    stringResource(R.string.access_control_description),
                    style = TextStyle(
                        fontFamily = inter,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 14.sp,
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(accessItems) { item ->
                        AccessItemCard(
                            item = item,
                            isChecked = isChecked,
                            modifier = Modifier.width(150.dp).height(112.dp)
                        ) {
                            isChecked = it
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun AccessControlScreenPreview() {
    AccessControlScreen({}, {})
}