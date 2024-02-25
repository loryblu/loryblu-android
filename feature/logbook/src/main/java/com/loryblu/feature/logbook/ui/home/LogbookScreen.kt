package com.loryblu.feature.logbook.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.theme.LBContentHome
import com.loryblu.data.logbook.local.getAllShiftItems
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.ui.components.FrequencyBar
import com.loryblu.feature.logbook.ui.components.ShiftBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogbookScreen(
    onBackButtonClicked: () -> Unit,
    onNextScreenClicked: () -> Unit,
    userTasks: ApiResponseWithData<List<LogbookTask>>,
    selectADayOfWeek: (Int) -> Unit,
) {

    val selectedDay = remember {
        mutableStateListOf<Int>(1)
    }
    val shiftSelected = remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            LBTopAppBar(
                title = stringResource(R.string.logbook_title),
                onBackClicked = { onBackButtonClicked() },
                onCloseClicked = { onBackButtonClicked() },
                showCloseButton = true
            )
        },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {

                TasksSelector(
                    selectedDay = selectedDay,
                    shiftSelected = shiftSelected.value,
                    onShiftChange = selectADayOfWeek
                )

                Spacer(modifier = Modifier.height(50.dp))
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.85f)
                ) {
                    NoAssignmentsLayout()
                }
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    ExtendedFloatingActionButton(
                        modifier = Modifier
                            .padding(bottom = 16.dp, end = 16.dp),
                        containerColor = LBContentHome,
                        onClick = {
                            onNextScreenClicked()
                        },
                        shape = ShapeDefaults.ExtraLarge
                    ) {
                        Text(
                            text = stringResource(R.string.new_task),
                            color = Color.White
                        )
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = stringResource(R.string.new_task),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun ColumnScope.NoAssignmentsLayout(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(com.loryblu.data.logbook.R.drawable.screen_home_logbook),
        contentDescription = null,
        modifier = Modifier
            .width(327.dp)
            .height(302.dp)
            .align(Alignment.CenterHorizontally),
        contentScale = ContentScale.Crop
    )
    Text(
        fontSize = 18.sp,
        text = stringResource(R.string.no_task_found),
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(
                top = 28.dp,
                end = 9.dp
            )
            .align(Alignment.CenterHorizontally)
    )
}

@Composable
fun TasksSelector(
    selectedDay: List<Int>,
    shiftSelected: Int,
    onShiftChange: (Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        FrequencyBar(
            selectedDay = selectedDay,
            onDayClicked = {
                onShiftChange(it)
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        ShiftBar(
            modifier = Modifier.padding(horizontal = 10.dp),
            shiftSelected = shiftSelected,
            onShiftChange = {},
            options = getAllShiftItems()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeLogbookScreenPreview() {
    LogbookScreen(
        onBackButtonClicked = {},
        onNextScreenClicked = {},
        userTasks = ApiResponseWithData.Default(),
        selectADayOfWeek = {},
    )
}