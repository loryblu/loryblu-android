package com.loryblu.feature.logbook.ui.task.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.components.LBMediumButton
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.model.EditResult
import com.loryblu.feature.logbook.model.LogbookTaskModel
import com.loryblu.feature.logbook.ui.task.LoadingScreen
import com.loryblu.feature.logbook.ui.task.TaskSummaryContent
import com.loryblu.feature.logbook.utils.getDaySelected

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditTaskSummaryScreen(
    logbookTaskModel: LogbookTaskModel,
    onBackButtonClicked: () -> Unit,
    onFrequencyChange: (List<Int>) -> Unit,
    onShiftChange: (Int) -> Unit,
    onCategoryNavigate: () -> Unit,
    onTaskNavigate: () -> Unit,
    onTaskSaveClicked: () -> Unit,
    editResult: EditResult,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var selectedFrequency by remember { mutableStateOf(getDaySelected(logbookTaskModel.frequency)) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LBTopAppBar(
                scrollBehavior = scrollBehavior,
                title = stringResource(R.string.edit_task_title),
                onBackClicked = onBackButtonClicked,
                showCloseButton = false
            )
        },
        content = { innerPadding ->
            if (editResult is EditResult.Success) {
                TaskSummaryContent(
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
                    EditSummaryButtons(
                        onCancel = onBackButtonClicked,
                        onSave = onTaskSaveClicked,
                        saveIsEnabled = selectedFrequency.isNotEmpty()
                    )
                }
            } else {
                LoadingScreen()
            }
        }
    )
}

@Composable
fun EditSummaryButtons(onCancel: () -> Unit, onSave: () -> Unit, saveIsEnabled: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        LBMediumButton(
            modifier = Modifier.weight(1f),
            textRes = R.string.cancel,
            onClick = onCancel,
            backgroundColor = Color.White,
            borderColor = LBSkyBlue,
            textColor = LBSkyBlue
        )
        Spacer(modifier = Modifier.width(16.dp))
        LBMediumButton(
            modifier = Modifier.weight(1f),
            onClick = onSave,
            textRes = R.string.save,
            backgroundColor = LBSkyBlue,
            borderColor = LBSkyBlue,
            textColor = Color.White,
            enabled = saveIsEnabled
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditSummaryPreview() {
    EditTaskSummaryScreen(
        logbookTaskModel = LogbookTaskModel(
            category = CategoryItem.Routine,
            task = "6dfc15bb-f422-4c75-b2cc-bf3e9806c76a",
            shift = "morning",
            frequency = listOf("sun", "mon")
        ),
        onBackButtonClicked = {},
        onFrequencyChange = {},
        onShiftChange = {},
        onCategoryNavigate = {},
        onTaskNavigate = {},
        onTaskSaveClicked = {},
        editResult = EditResult.Success
    )
}

