package com.loryblu.core.ui.components.text_fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBShadowGray

@Composable
fun TextFieldWithTitle(
    title: String,
    textField: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            color = LBShadowGray,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        textField()
    }
}
