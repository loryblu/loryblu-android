@file:OptIn(ExperimentalMaterial3Api::class)

package com.loryblu.feature.logbook.ui.task

import LBProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.components.LBTaskCard
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.data.logbook.local.RoutineTaskItem
import com.loryblu.data.logbook.local.getAllRoutineItems
import com.loryblu.data.logbook.local.getAllShiftItems
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.model.Category
import com.loryblu.feature.logbook.model.LogbookTaskModel
import com.loryblu.feature.logbook.ui.components.FrequencyBar
import com.loryblu.feature.logbook.ui.components.ShiftBar

@Composable
fun SummaryScreen(
    onBackButtonClicked: () -> Unit,
    onCloseButtonClicked: () -> Unit,
    logbookTaskModel: LogbookTaskModel,
    onNextScreenClicked: () -> Unit,
) {

    val shiftSelected = getShiftSelected(logbookTaskModel.shift)
    val selectedDay = getDaySelected(logbookTaskModel.frequency)

    Scaffold(
        topBar = {
            LBTopAppBar(
                title = stringResource(R.string.logbook_title),
                onBackClicked = onBackButtonClicked,
                onCloseClicked = onCloseButtonClicked,
                showCloseButton = true
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LBProgressBar(modifier = Modifier.fillMaxWidth(), currentStep = 4)
                Spacer(modifier = Modifier.size(8.dp))
                getTask(taskId = logbookTaskModel.task)?.let { taskItem ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .heightIn()
                    ) {
                        val painter = painterResource(taskItem.drawable)
                        Spacer(Modifier.weight(0.25f))
                        LBTaskCard(
                            card = taskItem,
                            modifier = Modifier
                                .size(150.dp)
                                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                                .fillMaxWidth(),
                            selected = true,
                            onclick = { })
                        Spacer(Modifier.weight(0.25f))
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 24.dp),
                            text = stringResource(R.string.categoryTitle),
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight(500),
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                            )
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(LBDarkBlue)
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.Center),
                                text = stringResource(id = taskItem.text),
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    lineHeight = 24.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 24.dp), text = stringResource(R.string.taskTitle), style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight(500),
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                            )
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(LBDarkBlue)
                        )
                        {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.Center),
                                text = logbookTaskModel.category.action,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    lineHeight = 16.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 24.dp)
                        .drawWithContent {
                            drawContent()
                            drawLine(
                                color = LBLightGray,
                                start = Offset(0.dp.toPx(), size.height / 2),
                                end = Offset(size.width, size.height / 2),
                                strokeWidth = 2.dp.toPx(),
                                cap = StrokeCap.Round
                            )
                        }
                        .graphicsLayer(clip = true),
                    thickness = 2.dp
                )

                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = stringResource(R.string.shiftTitle), style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                    )
                )
                Spacer(modifier = Modifier.size(8.dp))
                ShiftBar(
                    modifier = Modifier.fillMaxWidth(),
                    shiftSelected = shiftSelected,
                    onShiftChange = { },
                    options = getAllShiftItems()
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                    ), modifier = Modifier
                        .padding(bottom = 4.dp)
                        .align(Alignment.Start),
                    text = stringResource(R.string.frequencyTitle)
                )
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = stringResource(id = R.string.select_a_frequency_description),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                    )
                )
                Spacer(modifier = Modifier.size(8.dp))
                FrequencyBar(selectedDay = selectedDay, onDayClicked = { })
            }
            Box(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp)) {
                LBButton(
                    textRes = R.string.registerTask,
                    onClick = {
                    },
                    buttonColors = ButtonDefaults.buttonColors(
                        disabledContainerColor = LBLightGray,
                        containerColor = LBSkyBlue
                    ),
                    textColor = Color.White,
                    areAllFieldsValid = true
                )
            }
        }
    }
}

@Composable
private fun getTask(taskId: String): RoutineTaskItem? {
    val tasks = getAllRoutineItems()
    val task = tasks.find {
        it.taskId == taskId
    }
    return task
}

@Composable
private fun getShiftSelected(shift: String): Int {
    return when (shift) {
        "morning" -> {
            0
        }

        "afternoon" -> {
            1
        }

        else -> {
            2
        }
    }
}

@Composable
private fun getDaySelected(days: List<String>): List<Int> {
    val selectedDays = remember { mutableStateListOf<Int>() }
    val nameOfWeekDays = arrayOf("sun", "mon", "tue", "wed", "thu", "fri", "sat")
    nameOfWeekDays.forEachIndexed { index, day ->
        if (days.any { it.contains(day) }) {
            selectedDays.add(index)
        }
    }
    return selectedDays
}

@Preview(showBackground = true)
@Composable
fun SummaryPreview() {
    SummaryScreen(
        onBackButtonClicked = { },
        onCloseButtonClicked = { },
        logbookTaskModel = LogbookTaskModel(
            category = Category.ROUTINE,
            task = "6dfc15bb-f422-4c75-b2cc-bf3e9806c76a",
            shift = "morning",
            frequency = listOf("sun", "mon")
        ),
        onNextScreenClicked = { },
    )
}