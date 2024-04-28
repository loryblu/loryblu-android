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
import com.loryblu.feature.logbook.model.LogbookTaskModel
import com.loryblu.feature.logbook.ui.task.TaskSummaryContent

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
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

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
            TaskSummaryContent(
                logbookTaskModel = logbookTaskModel,
                innerPadding = innerPadding,
                onShiftChange = onShiftChange,
                onFrequencyChange = onFrequencyChange,
                onCategoryNavigate = onCategoryNavigate,
                onTaskNavigate = onTaskNavigate,
            ) {
                EditSummaryButtons(
                    onCancel = onBackButtonClicked,
                    onSave = onTaskSaveClicked,
                )
            }
        }
    )
}

@Composable
fun EditSummaryButtons(onCancel: () -> Unit, onSave: () -> Unit) {
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
            textColor = Color.White
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
    )
}

