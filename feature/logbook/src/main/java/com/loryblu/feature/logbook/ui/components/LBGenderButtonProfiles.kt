package com.loryblu.feature.logbook.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBMediumGray
import com.loryblu.core.ui.theme.LBSilverGray
import com.loryblu.core.ui.theme.LBSoftBlue
import com.loryblu.core.ui.theme.LBSoftGray

@Composable
fun RowScope.LBGenderButtonProfiles(
    isEditable: Boolean,
    onClick: () -> Unit,
    @DrawableRes iconRes: Int,
    @StringRes textRes: Int,
    isSelected: Boolean,
) {

    val containerColor = getContainerColor(isEditable, isSelected)
    val borderColor = getBorderColor(isEditable, isSelected)
    val iconTextColor = getIconTextColor(isEditable, isSelected)

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.weight(1f),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = containerColor,
        ),
        border = BorderStroke(
            width = 2.dp,
            color = borderColor
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = iconTextColor,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(textRes),
                color = iconTextColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )

            if (isSelected && !isEditable) {
                Spacer(Modifier.weight(1f))

                Icon(
                    painter = painterResource(id = R.drawable.ic_padlock_close),
                    contentDescription = null,
                    tint = LBDarkBlue,
                )
            }
        }
    }

}

private fun getContainerColor(
    isEditable: Boolean,
    isSelected: Boolean
): Color {
    val isDarkBlue = isEditable && isSelected
    val isLightGray = !isEditable && isSelected

    return when {
        isDarkBlue -> LBDarkBlue
        isLightGray -> LBLightGray.copy(alpha = 0.6f)
        else -> LBSoftGray
    }
}

private fun getBorderColor(
    isEditable: Boolean,
    isSelected: Boolean
): Color {
    val isDarkBlue = isEditable && isSelected
    val isLightGray = !isEditable && isSelected

    return when {
        isDarkBlue -> LBDarkBlue
        isLightGray -> LBLightGray.copy(alpha = 0.6f)
        else -> LBLightGray
    }
}

private fun getIconTextColor(
    isEditable: Boolean,
    isSelected: Boolean
): Color {
    val isSoftBlue = isEditable && isSelected
    val isMediumGray = !isEditable && isSelected

    return when {
        isSoftBlue -> LBSoftBlue
        isMediumGray -> LBMediumGray
        else -> LBSilverGray
    }
}

@Preview(showBackground = true)
@Composable
private fun LBGenderButtonProfilesPreview() {
    Column {
        // Editable
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LBGenderButtonProfiles(
                isEditable = true,
                onClick = {},
                iconRes = R.drawable.ic_boy,
                textRes = R.string.boy,
                isSelected = true
            )

            LBGenderButtonProfiles(
                isEditable = true,
                onClick = {},
                iconRes = R.drawable.ic_girl,
                textRes = R.string.girl,
                isSelected = false
            )
        }

        // Not editable
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LBGenderButtonProfiles(
                isEditable = false,
                onClick = {},
                iconRes = R.drawable.ic_boy,
                textRes = R.string.boy,
                isSelected = true
            )

            LBGenderButtonProfiles(
                isEditable = false,
                onClick = {},
                iconRes = R.drawable.ic_girl,
                textRes = R.string.girl,
                isSelected = false
            )
        }
    }
}
