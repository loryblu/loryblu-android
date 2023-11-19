package com.loryblu.feature.home

import LBProgressBar
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.loryblu.data.logbook.model.getAllLogbookItems

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen() {

    val category = getAllLogbookItems()
    Scaffold(
        topBar = {
            LBTopAppBar(
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
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LBProgressBar(currentStep = 1)
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, bottom = 12.dp)
                ){
                    Text(
                        color = LBDarkBlue,
                        text = stringResource(R.string.select_a_category),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    )
                }
                LazyColumn(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    items(category){
                        LBCard(
                            card = it,
                            modifier = Modifier.height(228.dp),
                            onclick = {
                                //do something
                            }
                        )
                    }
                }
                Column(
                    modifier = Modifier.padding(24.dp)
                ){
                    LBButton(
                        textRes = R.string.next,
                        onClick = { /*TODO*/ },
                        buttonColors = ButtonDefaults.buttonColors(
                            disabledContainerColor = LBSkyBlue,
                            containerColor = LBSkyBlue),
                        textColor = Color.White,
                        areAllFieldsValid = true
                    )
                }
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    CategoryScreen()
}