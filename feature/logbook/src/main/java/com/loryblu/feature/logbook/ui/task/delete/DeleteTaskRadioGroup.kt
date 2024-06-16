package com.loryblu.feature.logbook.ui.task.delete

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.loryblu.feature.home.R
import java.util.Locale

@Composable
fun DeleteTaskRadioGroup(
    selectedDay: Int,
    deleteOption: DeleteOption,
    onChangeDeleteOption: (option: DeleteOption) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = (deleteOption is DeleteOption.SingleDay),
                onClick = { onChangeDeleteOption(DeleteOption.SingleDay(selectedDay)) },
            )
            Text(
                text = "${stringResource(id = R.string.only_on)} ${getWeekdaysNames()[selectedDay]}",
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = (deleteOption == DeleteOption.Everyday),
                onClick = { onChangeDeleteOption(DeleteOption.Everyday) },
            )
            Text(
                text = stringResource(id = R.string.everyday),
            )
        }
    }
}

fun getWeekdaysNames(): List<String> {
    return when (Locale.getDefault().language) {
        "pt" -> listOf(
            "domingo",
            "segunda-feira",
            "terça-feira",
            "quarta-feira",
            "quinta-feira",
            "sexta-feira",
            "sábado",
        )

        else -> listOf(
            "sunday",
            "monday",
            "tuesday",
            "wednesday",
            "thursday",
            "friday",
            "saturday",
        )
    }
}