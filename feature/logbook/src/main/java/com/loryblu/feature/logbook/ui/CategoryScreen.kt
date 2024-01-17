package com.loryblu.feature.logbook.ui

import LBProgressBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.components.LBCategoryCard
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.data.logbook.local.getAllCategoryItems
import com.loryblu.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    onBackButtonClicked: () -> Unit,
    onNextScreenClicked: () -> Unit,
    onCloseButtonClicked: () -> Unit,
) {

    var cardClicked by rememberSaveable {
        mutableStateOf(-1)
    }

    val category = getAllCategoryItems()
    Scaffold(
        topBar = {
            LBTopAppBar(
                title = stringResource(R.string.logbook_title),
                onBackClicked = { onBackButtonClicked() },
                onCloseClicked = { onCloseButtonClicked() },
                showCloseButton = true
            )
        },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
            ) {
                LBProgressBar(modifier = Modifier.padding(vertical = 24.dp), currentStep = 1)
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text(
                        color = LBDarkBlue,
                        text = stringResource(R.string.select_a_category),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    items(category) {
                        LBCategoryCard(
                            card = it,
                            modifier = Modifier.height(228.dp),
                            selected = cardClicked == it.idCard,
                            onclick = {
                                cardClicked = it.idCard
                            }
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(vertical = 24.dp)
                ) {
                    LBButton(
                        textRes = com.loryblu.core.ui.R.string.next,
                        onClick = { onNextScreenClicked() },
                        buttonColors = ButtonDefaults.buttonColors(
                            disabledContainerColor = LBSkyBlue,
                            containerColor = LBSkyBlue
                        ),
                        textColor = Color.White,
                        areAllFieldsValid = true
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    CategoryScreen(
        onNextScreenClicked = {},
        onBackButtonClicked = {},
        onCloseButtonClicked = {}
    )
}