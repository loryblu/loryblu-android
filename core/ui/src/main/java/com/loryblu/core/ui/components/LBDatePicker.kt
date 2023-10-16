package com.loryblu.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBErrorColor
import com.loryblu.core.ui.theme.LBSoftGray
import com.loryblu.core.ui.theme.LBMediumGray
import com.loryblu.core.ui.theme.LBSmokeGray
import com.loryblu.core.ui.theme.LBSilverGray
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.core.ui.theme.LBSoftBlue
import com.loryblu.core.util.extensions.toDateFormat
import com.loryblu.core.util.extensions.toHeadLineDateFormat
import com.loryblu.core.util.validators.BirthdayInputValid
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@ExperimentalMaterial3Api
@Composable
fun LBDatePicker(
    placeholderRes: String,
    error: BirthdayInputValid,
    onBirthdayChange: (String) -> Unit,
    fieldFocus: (Boolean) -> Unit = {},
) {

    val focusManager = LocalFocusManager.current

    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = ZonedDateTime.ofInstant(
            Instant.now(),
            ZoneId.systemDefault()
        ).toInstant().toEpochMilli(),
        yearRange = 2013..2023
    )

    var showDate by rememberSaveable {
        mutableStateOf("")
    }

    val confirmEnabled by remember {
        derivedStateOf {
            datePickerState.selectedDateMillis != null
        }
    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                showDate = millis.toDateFormat()
                            }
                        showDatePickerDialog = false
                        onBirthdayChange(showDate)
                        focusManager.clearFocus()
                    },
                    enabled = confirmEnabled
                ) {
                    Text(
                        text = stringResource(R.string.ok),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = LBDarkBlue
                        )
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePickerDialog = false
                        focusManager.clearFocus()
                    },
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = LBDarkBlue
                        )
                    )
                }
            },
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = LBDarkBlue,
                    shape = RoundedCornerShape(24.dp)
                ),
            colors = DatePickerDefaults.colors(
                containerColor = LBSoftGray,
            )
        ) {
            DatePicker(
                state = datePickerState,
                headline = {
                    Text(
                        text = datePickerState
                            .selectedDateMillis!!
                            .toHeadLineDateFormat(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                    )
                },
                title = {
                    Text(
                        text = stringResource(R.string.select_the_date),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 36.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = LBSmokeGray
                    )
                },
                colors = DatePickerDefaults.colors(
                    containerColor = LBSoftGray,
                    weekdayContentColor = LBShadowGray,
                    navigationContentColor = LBMediumGray,
                    dividerColor = LBDarkBlue,
                    selectedDayContainerColor = LBDarkBlue,
                    selectedDayContentColor = LBSoftGray,
                    todayDateBorderColor = LBDarkBlue,
                    todayContentColor = LBDarkBlue,
                    yearContentColor = LBShadowGray,
                    selectedYearContainerColor = LBDarkBlue,
                    dateTextFieldColors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LBSoftBlue,
                        focusedContainerColor = LBSoftBlue,
                        disabledContainerColor = LBSoftBlue,
                        errorContainerColor = LBSoftBlue,
                        unfocusedBorderColor = LBSoftBlue,
                        focusedBorderColor = LBSoftBlue,
                        disabledBorderColor = LBSoftBlue,
                        errorBorderColor = LBErrorColor,
                        cursorColor = LBSilverGray,
                        focusedTextColor = LBSilverGray,
                        unfocusedTextColor = LBSilverGray,
                        disabledTextColor = LBSilverGray,
                        errorTextColor = LBErrorColor,
                        disabledLabelColor = LBSilverGray,
                        focusedLabelColor = LBSilverGray,
                        unfocusedLabelColor = LBSilverGray,
                    )
                ),
            )
        }
    }

    OutlinedTextField(
        onValueChange = { },
        value = showDate,
        placeholder = {
            Text(
                text = placeholderRes,
                color = if (error is BirthdayInputValid.Error) LBSilverGray else LBSilverGray,
                fontWeight = FontWeight.Bold
            )
        },
        textStyle = TextStyle(
            color = LBSilverGray,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_birthday_cake),
                contentDescription = stringResource(R.string.birthday_icon),
                tint = LBSilverGray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusEvent { focusState ->
                if (focusState.isFocused) {
                    showDatePickerDialog = true
                    fieldFocus(focusState.isFocused)
                }
            },
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = LBSilverGray,
            unfocusedBorderColor = LBSoftBlue,
            disabledBorderColor = LBSoftBlue,
            errorContainerColor = LBSoftBlue,
            disabledContainerColor = LBSoftBlue,
            focusedContainerColor = LBSoftBlue,
            unfocusedContainerColor = LBSoftBlue,
            focusedBorderColor = LBSoftBlue,
        ),
        readOnly = true,
        isError = error is BirthdayInputValid.Error,
    )
}
