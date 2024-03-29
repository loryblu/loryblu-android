package com.loryblu.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBDarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LBTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    title: String,
    onBackClicked: () -> Unit,
    onCloseClicked: () -> Unit = { },
    showCloseButton: Boolean
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        modifier = Modifier
            .padding(horizontal = 5.dp),
        title = {
            Text(
                text = title,
                color = LBDarkBlue,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = LBDarkBlue
                )
            }
        },
        actions = {
            if(showCloseButton) {
                IconButton(onClick = { onCloseClicked() }) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null,
                        tint = LBDarkBlue,
                    )
                }
            }
        }
    )
}
