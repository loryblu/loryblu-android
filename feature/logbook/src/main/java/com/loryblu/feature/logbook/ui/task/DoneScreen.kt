package com.loryblu.feature.logbook.ui.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.feature.home.R

@Composable
fun DoneScreen(
    onCloseButtonClicked: () -> Unit
) {
    Scaffold(
        topBar = {
           Box(Modifier.fillMaxWidth()) {
               IconButton(modifier = Modifier.align(Alignment.BottomEnd).padding(top = 24.dp,end = 24.dp),onClick = onCloseButtonClicked) {
                   Icon(modifier = Modifier.size(80.dp),imageVector = Icons.Default.Close, contentDescription = null, tint = LBDarkBlue)
               }
           }
        }) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.align(Alignment.Center).fillMaxSize()) {
                Text(fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp),text = "cadastro efetuado com sucesso!", color = LBShadowGray)
                Spacer(modifier = Modifier.size(32.dp))
                Image(modifier = Modifier.align(Alignment.CenterHorizontally), painter = painterResource(id = R.drawable.icone_cadastro_com_sucesso), contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
fun DoneScreenPreview() {
    DoneScreen { }
}