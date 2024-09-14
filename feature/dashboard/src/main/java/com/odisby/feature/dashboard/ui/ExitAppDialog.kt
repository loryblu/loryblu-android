package com.odisby.feature.dashboard.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.components.LBNegativeDialogButton
import com.loryblu.core.ui.components.LBTransparentButton
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBSoftBlue
import com.odisby.feature.dashboard.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExitAppDialog(
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
) {
    BasicAlertDialog(onDismissRequest = onCancel) {
        Card(
            modifier = Modifier.size(240.dp, 182.dp),
            colors = CardDefaults.cardColors(containerColor = LBSoftBlue),
        ) {
            Column(
                modifier = Modifier.padding(24.dp).fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    val thisFontWeight = FontWeight.Bold
                    val thisTextAlign = TextAlign.Center
                    val thisStyle = MaterialTheme.typography.titleMedium

                    Text(
                        text = stringResource(id = R.string.are_you_sure),
                        textAlign = thisTextAlign,
                        fontWeight = thisFontWeight,
                        style = thisStyle,
                    )
                    Text(
                        text = stringResource(id = R.string.exit_app_question),
                        textAlign = thisTextAlign,
                        fontWeight = thisFontWeight,
                        color = LBDarkBlue,
                        style = thisStyle,
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    LBTransparentButton(
                        onClick = onCancel,
                        textRes = R.string.cancel,
                    )
                    LBNegativeDialogButton(
                        onClick = onConfirm,
                        textRes = R.string.exit,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskDeletedDialogPreview() {
    ExitAppDialog(onCancel = {}, onConfirm = {})
}
