package com.odisby.feature.dashboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.components.buttons.LBNegativeDialogButton
import com.loryblu.core.ui.components.buttons.LBTransparentButton
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
            modifier = Modifier.width(240.dp).height(183.dp),
            colors = CardDefaults.cardColors(containerColor = LBSoftBlue),
        ) {
            Box(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.width(208.dp).height(118.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
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
                            buildAnnotatedString {
                                append(stringResource(id = R.string.exit_app_question))
                                withStyle(style = SpanStyle(color = Color.Black)) {
                                    append("?")
                                }
                            },
                            textAlign = thisTextAlign,
                            fontWeight = thisFontWeight,
                            color = LBDarkBlue,
                            style = thisStyle,
                        )
                    }

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
}

@Preview
@Composable
fun TaskDeletedDialogPreview() {
    ExitAppDialog(onCancel = {}, onConfirm = {})
}
