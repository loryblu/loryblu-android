package com.loryblu.feature.logbook.ui.task

import LBProgressBar
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.data.logbook.local.TaskItem
import com.loryblu.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(
    onBackButtonClicked: () -> Unit,
    onNextScreenClicked: (categoryId: String) -> Unit,
    onCloseButtonClicked: () -> Unit,
    category: CategoryItem,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var cardClicked by rememberSaveable {
        mutableIntStateOf(-1)
    }

    val taskItems = TaskItem.getAllTaskItems().filter { it.category == category }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            LBTopAppBar(
                scrollBehavior = scrollBehavior,
                title = stringResource(R.string.logbook_title),
                onBackClicked = { onBackButtonClicked() },
                onCloseClicked = { onCloseButtonClicked() },
                showCloseButton = true
            )
        },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
            ) {
                LBProgressBar(modifier = Modifier.padding(vertical = 24.dp), currentStep = 2)
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
                                onclick = {
                                    cardClicked = it.idCard
                                }
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
    )
}

@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    TaskScreen(
        onBackButtonClicked = {},
        onNextScreenClicked = {},
        onCloseButtonClicked = {},
        category = CategoryItem.Routine
    )
}