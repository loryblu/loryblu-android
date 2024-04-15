package com.loryblu.feature.logbook.ui.task.edit

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
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.ui.task.CategoryContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCategoryScreen(
    onBackButtonClicked: () -> Unit,
    onNextScreenClicked: (category: CategoryItem) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    var cardClicked by rememberSaveable {
        mutableIntStateOf(-1)
    }

    val category = CategoryItem.getAllCategory()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LBTopAppBar(
                scrollBehavior = scrollBehavior,
                title = stringResource(R.string.edit_task_title),
                onBackClicked = { onBackButtonClicked() },
                showCloseButton = false
            )
        },
        content = { innerPadding ->
            CategoryContent(
                innerPadding = innerPadding,
                cardClicked = cardClicked,
                onCardClick = {},
                onNextScreenClicked = onNextScreenClicked
            )
        }
    )
}