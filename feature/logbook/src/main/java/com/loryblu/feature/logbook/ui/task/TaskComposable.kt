package com.loryblu.feature.logbook.ui.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBCardSoftBlue
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.local.TaskItem
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.model.LogbookTaskModel
import com.loryblu.feature.logbook.ui.components.FrequencyBar
import com.loryblu.feature.logbook.ui.components.ShiftBar

@Composable
fun TaskSummaryContent(
    logbookTaskModel: LogbookTaskModel,
    innerPadding: PaddingValues,
    onFrequencyChange: (List<Int>) -> Unit,
    onShiftChange: (Int) -> Unit,
    onCategoryNavigate: () -> Unit,
    onTaskNavigate: () -> Unit,
    topContent: @Composable () -> Unit = {},
    buttons: @Composable () -> Unit,
) {

    var shiftSelected = 0
    var selectedDay = listOf<Int>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            topContent.invoke()
            Spacer(modifier = Modifier.size(8.dp))
            getTask(taskId = logbookTaskModel.task)?.let { taskItem ->
                Row(
                    Modifier
                        .padding(16.dp)
                        .background(color = LBCardSoftBlue, shape = RoundedCornerShape(8.dp))
                        .border(4.dp, LBDarkBlue, shape = RoundedCornerShape(8.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Image(
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .padding(16.dp)
                            .alpha(1f)
                            .height(108.dp)
                            .width(110.dp),
                        painter = painterResource(id = taskItem.drawable),
                        contentDescription = stringResource(id = taskItem.text),
                        contentScale = ContentScale.Fit,
                    )
                }

                Spacer(modifier = Modifier.size(8.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.categoryTitle),
                            style = TextStyle(
                                fontSize = 24.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(500),
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                            )
                        )
                        Column(
                            modifier = Modifier
                                .background(LBDarkBlue, RoundedCornerShape(8.dp))
                                .width(140.dp)
                                .height(46.dp)
                                .clickable {
                                    onCategoryNavigate()
                                },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = logbookTaskModel.category.text),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 18.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color.White,
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.taskTitle),
                            style = TextStyle(
                                fontSize = 24.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(500),
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                            )
                        )
                        Column(
                            modifier = Modifier
                                .background(LBDarkBlue, RoundedCornerShape(8.dp))
                                .width(140.dp)
                                .height(46.dp)
                                .clickable {
                                    onTaskNavigate()
                                },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = taskItem.text),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 18.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color.White,
                                )
                            )
                        }
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
                    fontSize = 24.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                )
            )

            Spacer(modifier = Modifier.size(8.dp))

            ShiftBar(
                modifier = Modifier.fillMaxWidth(),
                shiftSelected = shiftSelected,
                onShiftChange = {
                    onShiftChange(it)
                    shiftSelected = it
                },
                options = ShiftItem.getShiftItems()
            )

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 18.sp,
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
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                )
            )
            Spacer(modifier = Modifier.size(8.dp))

            FrequencyBar(
                selectedDay = selectedDay,
                onDayClicked = {
                    selectedDay = if (it in selectedDay) {
                        selectedDay - it
                    } else {
                        selectedDay + it
                    }
                    onFrequencyChange(selectedDay)
                }
            )
            Spacer(Modifier.weight(1f))
        }
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(24.dp)) {
            buttons.invoke()
        }
    }
}

@Composable
private fun getTask(taskId: String): TaskItem? {
    val tasks = TaskItem.getAllTaskItems()
    val task = tasks.find {
        it.taskId == taskId
    }
    return task
}