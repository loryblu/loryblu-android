package com.loryblu.feature.logbook.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBFrequencyBackgroundBlue
import com.loryblu.core.ui.theme.LBFrequencyTextBlue
import java.util.Locale

@Composable
fun FrequencyBar(modifier: Modifier = Modifier, selectedDay: Int, onClick: (Int) -> Unit) {
    val daysOfWeek = getInitialDaysOfWeek()
    Row(
        modifier = modifier
            .background(LBFrequencyBackgroundBlue, shape = RoundedCornerShape(16))
            .padding(horizontal = 4.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        daysOfWeek.forEachIndexed { index, day ->
            TextButton(
                modifier = Modifier
                    .defaultMinSize(minWidth = 50.dp, minHeight = 50.dp),
                onClick = { onClick(index + 1) },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                    if (index + 1 == selectedDay) LBFrequencyTextBlue else Color.Transparent
                )
            ) {
                Text(
                    text = day,
                    color =
                    if (index + 1 == selectedDay) LBFrequencyBackgroundBlue else LBFrequencyTextBlue,
                    fontSize = 20.sp)
            }
        }
    }
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
        selectedDay = 1,
        onClick = {},
    )
}