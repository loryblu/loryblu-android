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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.components.LBCategoryCard
import com.loryblu.core.ui.components.LBTaskCard
import com.loryblu.core.ui.theme.LBCardSoftBlue
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.local.TaskItem
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.model.LogbookTaskModel
import com.loryblu.feature.logbook.ui.components.FrequencyBar
import com.loryblu.feature.logbook.ui.components.ShiftBar
import com.loryblu.feature.logbook.utils.getDaySelected
import com.loryblu.feature.logbook.utils.shiftToInt

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

    var shiftSelected by remember {
        mutableIntStateOf(shiftToInt(logbookTaskModel.shift))
    }

    var selectedDay by remember {
        mutableStateOf(emptyList<Int>())
    }

    selectedDay = getDaySelected(logbookTaskModel.frequency)

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
fun TaskContent(
    innerPadding: PaddingValues,
    taskItems: List<TaskItem>,
    cardClicked: Int,
    onCardClick: (cardClicked: Int) -> Unit,
    onNextScreenClicked: (categoryId: String) -> Unit,
    topContent: @Composable () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(innerPadding)
            .padding(horizontal = 24.dp)
    ) {
        topContent.invoke()
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                color = LBDarkBlue,
                text = stringResource(R.string.select_a_task),
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold
            )
        }

        LazyVerticalGrid(
            userScrollEnabled = true,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .height(900.dp)
                .fillMaxSize()
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(taskItems) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LBTaskCard(
                        card = it,
                        modifier = Modifier.height(163.dp),
                        selected = cardClicked == it.idCard,
                        onclick = { onCardClick.invoke(it.idCard) }
                    )
                }
            }
        }
        Box(
            modifier = Modifier.padding(vertical = 24.dp)
        ) {
            LBButton(
                textRes = R.string.next,
                onClick = {
                    onNextScreenClicked(
                        taskItems[cardClicked].taskId
                    )
                },
                buttonColors = ButtonDefaults.buttonColors(
                    disabledContainerColor = LBLightGray,
                    containerColor = LBSkyBlue
                ),
                textColor = Color.White,
                areAllFieldsValid = cardClicked >= 0,
            )
        }
    }
}

@Composable
fun CategoryContent(
    innerPadding: PaddingValues,
    cardClicked: Int,
    onCardClick: (cardClicked: Int) -> Unit,
    onNextScreenClicked: (category: CategoryItem) -> Unit,
    topContent: @Composable () -> Unit = {},
) {
    val category = CategoryItem.getAllCategory()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 24.dp)
    ) {
        topContent.invoke()
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
                    onclick = { onCardClick.invoke(it.idCard) }
                )
            }
        }
        Column(
            modifier = Modifier.padding(vertical = 24.dp)
        ) {
            LBButton(
                textRes = com.loryblu.core.ui.R.string.next,
                onClick = { onNextScreenClicked(
                    when(cardClicked){
                        0 -> CategoryItem.Routine
                        1 -> CategoryItem.Student
                        else -> CategoryItem.Routine
                    }
                ) },
                buttonColors = ButtonDefaults.buttonColors(
                    disabledContainerColor = LBLightGray,
                    containerColor = LBSkyBlue
                ),
                textColor = Color.White,
                areAllFieldsValid = cardClicked >= 0
            )
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