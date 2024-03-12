package com.loryblu.feature.logbook.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBFrequencyBackgroundBlue
import com.loryblu.core.ui.theme.LBFrequencyTextBlue
import java.util.Locale

@Composable
fun FrequencyBar(
    modifier: Modifier = Modifier,
    selectedDay: List<Int>,
    onDayClicked: (Int) -> Unit
) {
    val daysOfWeek = getInitialDaysOfWeek()
    Row(
        modifier = modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .background(LBFrequencyBackgroundBlue, shape = RoundedCornerShape(16))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        daysOfWeek.forEachIndexed { index, day ->
            val isDaySelected = isDaySelected(index, selectedDay)
            TextButton(
                modifier = Modifier
                    .defaultMinSize(minWidth = 50.dp, minHeight = 50.dp),
                onClick = {
                    onDayClicked(index)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                    if (isDaySelected) LBFrequencyTextBlue else Color.Transparent
                )
            ) {
                Text(
                    text = day,
                    color =
                    if (isDaySelected) LBFrequencyBackgroundBlue else LBFrequencyTextBlue,
                    fontSize = 20.sp
                )
            }
        }
    }
}

fun isDaySelected(index: Int, selectedDay: List<Int>): Boolean {
    return selectedDay.map { it }.contains(index)

}

fun getInitialDaysOfWeek(): List<String> {
    return when (Locale.getDefault().language) {
        "pt" -> listOf("D", "S", "T", "Q", "Q", "S", "S")
        else -> listOf("S", "M", "T", "W", "T", "F", "S")
    }
}

@Preview(showBackground = true)
@Composable
private fun FrequencyBarPreview() {
    FrequencyBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        selectedDay = mutableListOf(2, 4),
        onDayClicked = { },
    )
}