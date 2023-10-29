package com.loryblu.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.components.LBTitle
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.core.ui.theme.LBSoftGray

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize()
        ) {
            LBTitle(textRes = R.string.welcome)

            Spacer(modifier = Modifier.height(32.dp))
            LBButton(
                textRes = R.string.logout,
                onClick = { viewModel.logout() },
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = LBSkyBlue
                ),
                textColor = LBSoftGray,
                areAllFieldsValid = true
            )
        }
    }
}
