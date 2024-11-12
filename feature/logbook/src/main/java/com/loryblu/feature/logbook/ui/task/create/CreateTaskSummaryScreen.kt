@file:OptIn(ExperimentalMaterial3Api::class)

package com.loryblu.feature.logbook.ui.task.create

import LBProgressBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.model.LogbookTaskModel
import com.loryblu.feature.logbook.ui.task.TaskSummaryContent
import com.loryblu.feature.logbook.utils.getDaySelected

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskSummaryScreen(
    onBackButtonClicked: () -> Unit,
    onCloseButtonClicked: () -> Unit,
    logbookTaskModel: LogbookTaskModel,
    onNextScreenClicked: () -> Unit,
    onFrequencyChange: (List<Int>) -> Unit,
    onShiftChange: (Int) -> Unit,
    onCategoryNavigate: () -> Unit,
    onTaskNavigate: () -> Unit,
) {
    var selectedFrequency by remember { mutableStateOf(
        getDaySelected(logbookTaskModel.frequency)
    ) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LBTopAppBar(
                title = stringResource(R.string.new_task_title),
                onBackClicked = onBackButtonClicked,
                onCloseClicked = onCloseButtonClicked,
                showCloseButton = true
            )
        },
        content = { innerPadding ->
            TaskSummaryContent(
                topContent = {
                    LBProgressBar(modifier = Modifier.fillMaxWidth(), currentStep = 4)
                },
                logbookTaskModel = logbookTaskModel,
                innerPadding = innerPadding,
                onShiftChange = onShiftChange,
                onFrequencyChange = { frequency ->
                    onFrequencyChange.invoke(frequency)
                    selectedFrequency = frequency
                },
                onCategoryNavigate = onCategoryNavigate,
                onTaskNavigate = onTaskNavigate,
            ) {
                LBButton(
                    textRes = R.string.registerTask,
                    onClick = { onNextScreenClicked() },
                    buttonColors = ButtonDefaults.buttonColors(
                        disabledContainerColor = LBLightGray,
                        containerColor = LBSkyBlue
                    ),
                    textColor = Color.White,
                    areAllFieldsValid = selectedFrequency.isNotEmpty()
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CreateTaskSummaryPreview() {
    CreateTaskSummaryScreen(
        onBackButtonClicked = {},
        onCloseButtonClicked = {},
        logbookTaskModel = LogbookTaskModel(
            category = CategoryItem.Routine,
            task = "6dfc15bb-f422-4c75-b2cc-bf3e9806c76a",
            shift = "morning",
            frequency = listOf("sun", "mon")
        ),
        onNextScreenClicked = {},
        onShiftChange = {},
        onCategoryNavigate = {},
        onTaskNavigate = {},
        onFrequencyChange = {}
    )
}
