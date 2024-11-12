package com.loryblu.feature.logbook.ui.task.create

import LBProgressBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.ui.task.CategoryContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskCategoryScreen(
    onBackButtonClicked: () -> Unit,
    onNextScreenClicked: (category: CategoryItem) -> Unit,
    onCloseButtonClicked: () -> Unit,
) {

    var cardClicked: Int by rememberSaveable {
        mutableIntStateOf(-1)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LBTopAppBar(
                title = stringResource(R.string.new_task_title),
                onBackClicked = { onBackButtonClicked() },
                onCloseClicked = { onCloseButtonClicked() },
                showCloseButton = true
            )
        },
        content = { innerPadding ->
            CategoryContent(
                topContent = {
                    LBProgressBar(modifier = Modifier.padding(vertical = 24.dp), currentStep = 1)
                },
                innerPadding = innerPadding,
                cardClicked = cardClicked,
                onCardClick = { idCard ->
                    cardClicked = idCard
                },
                onNextScreenClicked = onNextScreenClicked,
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    CreateTaskCategoryScreen(
        onNextScreenClicked = {},
        onBackButtonClicked = {},
        onCloseButtonClicked = {}
    )
}