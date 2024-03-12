package com.loryblu.feature.logbook.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
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
import androidx.compose.ui.unit.sp
import com.loryblu.data.logbook.local.ShiftItem

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
                        inactiveBorderColor = Color.Transparent
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
                    Text(
                        text = stringResource(id = shift.text),
                        fontSize = 18.sp,
                        color = if(shiftSelected == index) Color.Black else Color.Gray
                    )
                }
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ShiftBarPrev() {
    ShiftBar(
        modifier = Modifier.padding(horizontal = 8.dp),
        shiftSelected = 1,
        onShiftChange = {},
        options = ShiftItem.getShiftItems()
    )
}