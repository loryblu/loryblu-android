package com.loryblu.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBErrorColor
import com.loryblu.core.ui.theme.LBSilverGray
import com.loryblu.core.ui.theme.LBSoftGray

@Composable
fun RowScope.LBGenderButton(
    onClick: () -> Unit,
    iconRes: Int,
    contentDescriptionRes: Int,
    textRes: Int,
    genderSelected: Boolean,
    error: Boolean
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = Modifier
            .height(50.dp)
            .weight(1f)
            .fillMaxHeight(),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(end = 16.dp, start = 8.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (genderSelected) LBSoftGray else LBSilverGray,
            containerColor = if (genderSelected) LBDarkBlue else LBSoftGray
        ),
        border = BorderStroke(
            width = 2.dp,
            color = when {
                genderSelected -> LBDarkBlue
                error -> LBErrorColor
                else -> LBSilverGray
            }
        ),
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = stringResource(id = contentDescriptionRes),
            tint = if (genderSelected) LBSoftGray else LBSilverGray,
            modifier = Modifier.padding(end = 12.dp, start = 6.dp),
        )
        Text(
            text = stringResource(id = textRes),
            color = if (genderSelected) LBSoftGray else LBSilverGray,
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
    }
}