package com.loryblu.feature.logbook.ui

import LBProgressBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.loryblu.core.ui.components.LBCardShift
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.data.logbook.local.getAllShiftItems
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.ui.components.FrequencyBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShiftScreen(
    onBackButtonClicked: () -> Unit,
    onNextScreenClicked: () -> Unit,
    onCloseButtonClicked: () -> Unit,
) {

    var cardClicked by rememberSaveable {
        mutableStateOf(-1)
    }

    val shiftItems = getAllShiftItems()

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
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
            ) {
                LBProgressBar(modifier = Modifier.padding(vertical = 24.dp), currentStep = 3)
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Text(
                        color = LBDarkBlue,
                        text = stringResource(R.string.select_a_shift),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    shiftItems.forEach { item ->
                        LBCardShift(
                            card = item,
                            modifier = Modifier
                                .height(112.dp)
                                .width(100.dp),
                            clicked = cardClicked == item.idCard,
                            onclick = {
                                cardClicked = item.idCard
                            }
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp)
                ) {
                    Text(
                        color = LBDarkBlue,
                        text = stringResource(R.string.select_a_frequency),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        color = LBDarkBlue,
                        text = stringResource(R.string.select_a_frequency_description),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    FrequencyBar(modifier = Modifier.fillMaxWidth())
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 110.dp),
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    LBButton(
                        textRes = R.string.confirm,
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

@Preview
@Composable
private fun ShiftScreenPreview() {
    ShiftScreen(
        onBackButtonClicked = {},
        onNextScreenClicked = {},
        onCloseButtonClicked = {}
    )
}