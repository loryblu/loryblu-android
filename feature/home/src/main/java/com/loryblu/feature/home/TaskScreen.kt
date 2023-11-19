package com.loryblu.feature.home

import LBProgressBar
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.loryblu.core.ui.components.LBCard
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.data.logbook.R
import com.loryblu.data.logbook.model.getAllStudentItems

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val category = getAllStudentItems()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
            //.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LBTopAppBar(
                scrollBehavior = scrollBehavior,
                title = stringResource(R.string.logbook_home),
                onBackClicked = { },
                onCloseClicked = { },
                showCloseButton = true
            )
        },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                LBProgressBar(currentStep = 2)
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp)
                ) {
                    Text(
                        color = LBDarkBlue,
                        text = stringResource(R.string.select_a_category),
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
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(category) {
                        Box(
                            contentAlignment = Alignment.TopStart,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            LBCard(
                                card = it,
                                modifier = Modifier.height(163.dp),
                                onclick = {
                                    // do something
                                }
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier.padding(24.dp)
                ) {
                    LBButton(
                        textRes = R.string.next,
                        onClick = {
                            //do something
                },
                buttonColors = ButtonDefaults.buttonColors(
                    disabledContainerColor = LBSkyBlue,
                    containerColor = LBSkyBlue
                ),
                textColor = Color.White,
                areAllFieldsValid = true,
                )
            }
        }
}
)
}
@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    TaskScreen()
}