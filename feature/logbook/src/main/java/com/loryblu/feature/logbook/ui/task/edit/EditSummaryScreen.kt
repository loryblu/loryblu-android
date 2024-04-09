package com.loryblu.feature.logbook.ui.task.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.model.LogbookTaskModel
import com.loryblu.feature.logbook.ui.task.TaskSummaryContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditSummaryScreen(
    logbookTaskModel: LogbookTaskModel,
    onBackButtonClicked: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            LBTopAppBar(
                scrollBehavior = scrollBehavior,
                title = stringResource(R.string.edit_task_title),
                onBackClicked = { onBackButtonClicked() },
                showCloseButton = false
            )
        },
        content = { innerPadding ->
            TaskSummaryContent(
                logbookTaskModel = logbookTaskModel,
                innerPadding = innerPadding,
                onCategoryNavigate = {},
                onTaskNavigate = {},
                onShiftChange = {},
                onFrequencyChange = {}
            ) {

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EditSummaryPreview() {
    EditSummaryScreen(
        logbookTaskModel = LogbookTaskModel(
            category = CategoryItem.Routine,
            task = "6dfc15bb-f422-4c75-b2cc-bf3e9806c76a",
            shift = "morning",
            frequency = listOf("sun", "mon")
        ),
        onBackButtonClicked = {},
    )
}

