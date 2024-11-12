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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.core.ui.theme.LBSoftGray
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.local.TaskItem
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletedTaskDialog(
    task: LogbookTask,
    deleteOption: DeleteOption,
    onDismissRequest: () -> Unit,
) {
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
                        text = stringResource(id = R.string.task_deleted),
                        textAlign = thisTextAlign,
                        fontWeight = thisFontWeight,
                        style = thisStyle,
                    )

                    Text(
                        text = stringResource(id = task.itemOfCategory.text),
                        textAlign = thisTextAlign,
                        fontWeight = thisFontWeight,
                        color = LBDarkBlue,
                        style = thisStyle,
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = stringResource(id = R.string.successfully_deleted),
                        textAlign = TextAlign.Center
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        val deleteOptionText = when (deleteOption) {
                            DeleteOption.Everyday ->
                                stringResource(id = R.string.everyday)

                            is DeleteOption.SingleDay ->
                                getWeekdaysNames()[deleteOption.day]
                        }

                        Text(
                            text = deleteOptionText,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "!",
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                LBButton(
                    textRes = R.string.close,
                    onClick = onDismissRequest,
                    buttonColors = ButtonDefaults.buttonColors(
                        containerColor = LBSkyBlue,
                    ),
                    textColor = LBSoftGray,
                    areAllFieldsValid = true
                )
            }
        }
    }
}

@Preview
@Composable
fun TaskDeletedDialogPreview() {
    DeletedTaskDialog(
        task = LogbookTask(
            itemOfCategory = TaskItem.getAllTaskItems().first(),
            frequency = listOf(),
            id = 4601,
            order = 4727,
            shift = ShiftItem.getShiftItems().first(),
            updatedAt = "vis"
        ),
        onDismissRequest = { },
        deleteOption = DeleteOption.SingleDay(0)
    )
}