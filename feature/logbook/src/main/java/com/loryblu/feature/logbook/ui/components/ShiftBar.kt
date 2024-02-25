package com.loryblu.feature.logbook.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonBorder
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.local.getAllShiftItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShiftBar(
    modifier: Modifier = Modifier,
    shiftSelected: Int,
    onShiftChange: (Int) -> Unit,
    options: List<ShiftItem>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        SingleChoiceSegmentedButtonRow(modifier.fillMaxWidth()) {
            options.forEachIndexed { index, shift ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size,
                        baseShape = RoundedCornerShape(8.dp)
                    ),
                    onClick = { onShiftChange(index) },
                    selected = index == shiftSelected,
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = shift.color,
                        inactiveContainerColor = Color(0XFFE8EAFD),
                        activeBorderColor = Color.Transparent,
                    ),
                    icon = {
                        SegmentedButtonDefaults.Icon(
                            active = shiftSelected == index,
                            activeContent = {
                                Icon(
                                    painter = painterResource(id = shift.drawable),
                                    contentDescription = null,
                                    modifier = Modifier.size(SegmentedButtonDefaults.IconSize),
                                    tint = Color.Unspecified
                                )
                            }
                        )
                    },
                ) {
                    Text(stringResource(id = shift.text))
                }
            }

        }
    }
}

@Preview
@Composable
private fun ShiftBarPrev() {
    ShiftBar(
        shiftSelected = 1,
        onShiftChange = {},
        options = getAllShiftItems()
    )
}