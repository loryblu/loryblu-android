package com.loryblu.feature.logbook.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBLightGray

@Composable
fun ParentAccessSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedClick: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = checked,
            onCheckedChange = {
                onCheckedClick(it)
            },
            thumbContent =
            if (checked) {
                {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            } else {
                {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            },
            colors = SwitchDefaults.colors(
                checkedTrackColor = LBDarkBlue,
                checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                uncheckedTrackColor = LBLightGray,
                uncheckedThumbColor = Color.White,
                uncheckedBorderColor = Color.Transparent,
                checkedIconColor = Color.Black,
                uncheckedIconColor = LBLightGray
            )
        )
    }
}

@Preview
@Composable
private fun ParentAccessSwitchPreview() {
    ParentAccessSwitch(modifier = Modifier.fillMaxSize(), false, {})
}