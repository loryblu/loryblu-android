package com.loryblu.feature.logbook.ui.task.delete

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.components.LBOutlinedExcludeButton
import com.loryblu.core.ui.components.LBTextButton
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.local.TaskItem
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteTaskDialog(
    task: LogbookTask,
    selectedDay: Int,
    onDismissRequest: () -> Unit,
    onConfirmRequest: (deleteOption: DeleteOption) -> Unit,
) {
    val deleteOption: MutableState<DeleteOption> = remember { mutableStateOf(DeleteOption.SingleDay(selectedDay)) }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
    ) {
        Card {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    val thisFontWeight = FontWeight.Bold
                    val thisTextAlign = TextAlign.Center
                    val thisStyle = MaterialTheme.typography.titleMedium
                    Text(
                        text = stringResource(id = R.string.want_to_exclude),
                        textAlign = thisTextAlign,
                        fontWeight = thisFontWeight,
                        style = thisStyle,
                    )
                    Row {
                        Text(
                            text = stringResource(id = task.itemOfCategory.text),
                            textAlign = thisTextAlign,
                            fontWeight = thisFontWeight,
                            color = LBDarkBlue,
                            style = thisStyle,
                        )
                        Text(
                            text = " ?",
                            textAlign = thisTextAlign,
                            fontWeight = thisFontWeight,
                            style = thisStyle,
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(id = R.string.content_permanently_deleted),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                DeleteTaskRadioGroup(
                    selectedDay = selectedDay,
                    deleteOption = deleteOption.value,
                    onChangeDeleteOption = { selectedOption ->
                        deleteOption.value = selectedOption
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    LBTextButton(
                        onClick = onDismissRequest,
                        textRes = R.string.cancel,
                        buttonColors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.onSurface,
                            containerColor = Color.Transparent
                        ),
                    )

                    LBOutlinedExcludeButton(
                        onClick = { onConfirmRequest(deleteOption.value) },
                        textRes = R.string.exclude,
                        buttonColors = ButtonDefaults.buttonColors(
                            contentColor = MaterialTheme.colorScheme.error,
                            containerColor = Color.Transparent
                        ),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DeleteTaskDialogPreview() {
    DeleteTaskDialog(
        task = LogbookTask(
            itemOfCategory = TaskItem.getAllTaskItems().first(),
            frequency = listOf(),
            id = 4601,
            order = 4727,
            shift = ShiftItem.getShiftItems().first(),
            updatedAt = "vis"
        ),
        onDismissRequest = { },
        onConfirmRequest = { },
        selectedDay = 0
    )
}