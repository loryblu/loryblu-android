package com.loryblu.feature.logbook.ui.task.create

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.data.logbook.local.TaskItem
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.ui.task.TaskContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateTaskScreen(
    onBackButtonClicked: () -> Unit,
    onNextScreenClicked: (categoryId: String) -> Unit,
    onCloseButtonClicked: () -> Unit,
    category: CategoryItem,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var cardClicked by rememberSaveable { mutableIntStateOf(-1) }

    val taskItems = TaskItem.getAllTaskItems().filter { it.category == category }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LBTopAppBar(
                scrollBehavior = scrollBehavior,
                title = stringResource(R.string.new_task_title),
                onBackClicked = { onBackButtonClicked() },
                onCloseClicked = { onCloseButtonClicked() },
                showCloseButton = true
            )
        },
        content = { innerPadding ->
            TaskContent(
                innerPadding = innerPadding,
                taskItems = taskItems,
                cardClicked = cardClicked,
                onCardClick = { idCard ->
                    cardClicked = idCard
                },
                onNextScreenClicked = onNextScreenClicked
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CreateTaskPreview() {
    CreateTaskScreen(
        onBackButtonClicked = {},
        onNextScreenClicked = {},
        onCloseButtonClicked = {},
        category = CategoryItem.Routine
    )
}