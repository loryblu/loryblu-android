package com.example.loryblu.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.loryblu.R
import com.example.loryblu.register.child.ChildRegisterViewModel
import com.example.loryblu.util.BirthdayInputValid
import com.example.loryblu.util.NameInputValid

@ExperimentalMaterial3Api
@Composable
fun LBDatePicker(
    valueSelectedDate : String,
    viewModel: ChildRegisterViewModel,
    labelRes: String,
    error: BirthdayInputValid,
    ) {
    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState(yearRange = 2013..2023)
    var selectedDate by remember {
        mutableStateOf("")
    }
    val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
    val uiState by viewModel.uiState.collectAsState()
    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                selectedDate = millis.toBrazilianDateFormat()
                            }
                        showDatePickerDialog = false
                    },
                    enabled = confirmEnabled
                ) {
                    Text(text = stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showDatePickerDialog = false }) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
        ) {
            DatePicker(state = datePickerState, showModeToggle = false)
        }
    }

    OutlinedTextField(
        onValueChange = { newBirthday: String ->
            viewModel.run {
                updateBirthday(newBirthday)
                birthdayState()
            }
        },
        value = valueSelectedDate,
        label = { Text(text = labelRes) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_birthday_cake),
                contentDescription = stringResource(
                    R.string.birthday_icon
                )
            )
        },
        modifier = Modifier
            .width(352.dp)
            .onFocusEvent {
                if (it.isFocused) {
                    showDatePickerDialog = true
                    focusManager.clearFocus(force = true)
                }
            },
        shape = RoundedCornerShape(10.dp),
        readOnly = true,
        isError = uiState.birthdayState is BirthdayInputValid.Error,
        supportingText = {
            if(error is BirthdayInputValid.Error) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(error.messageId),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
    )
}