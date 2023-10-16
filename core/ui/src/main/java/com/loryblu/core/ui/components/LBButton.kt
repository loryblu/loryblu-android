package com.loryblu.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBSoftGray
import com.loryblu.core.ui.theme.LBSkyBlue

@Composable
fun LBButton(
    @StringRes textRes: Int,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() }, colors = ButtonDefaults.buttonColors(LBSkyBlue),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = stringResource(textRes),
            fontWeight = FontWeight.Bold,
            color = LBSoftGray,
            fontSize = 20.sp,
        )
    }
}