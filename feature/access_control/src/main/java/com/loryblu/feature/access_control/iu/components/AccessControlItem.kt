package com.loryblu.feature.access_control.iu.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBSoftBlue
import com.loryblu.core.ui.theme.inter
import com.loryblu.core.ui.theme.interLight
import com.loryblu.feature.access_control.R

@Composable
fun AccessControlItem(
    mediumText: String,
    smallText: String,
    @DrawableRes imageId: Int,
    isChecked: Boolean,
    onSwitchChange: (Boolean) -> Unit,
) {
    val shape = RoundedCornerShape(10.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LBSoftBlue, shape = shape)
            .border(1.dp, LBLightGray, shape = shape)
            .padding(vertical = 11.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AccessImage(
                imageId = imageId,
                contentDescription = stringResource(
                    id = R.string.access_control_image_description, mediumText
                )
            )
            CustomSwitch(
                checked = isChecked,
                onCheckedChange = onSwitchChange,
                description = stringResource(
                    id = R.string.access_control_switch_description, mediumText
                )
            )
        }
        Text(
            text = smallText,
            color = Color.Black,
            style = TextStyle(
                fontFamily = inter,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 14.52.sp,
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = mediumText,
            color = Color.Black,
            style = TextStyle(
                fontFamily = interLight,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 12.1.sp,
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
    }

}


@Composable
fun AccessImage(@DrawableRes imageId: Int, contentDescription: String) {
    Surface(
        modifier = Modifier
            .size(37.dp)
            .clip(CircleShape)
            .background(Color.White),
        shape = CircleShape
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = imageId),
                contentDescription = contentDescription,
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    width: Dp = 56.dp,
    height: Dp = 32.dp,
    thumbSize: Dp = 24.dp,
    thumbPadding: Dp = 4.dp,
    description: String = ""
) {

    val thumbOffset by animateDpAsState(
        targetValue = if (checked)
            width - thumbSize - thumbPadding
        else
            thumbPadding, label = description
    )

    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(50))
            .background(if (checked) LBDarkBlue else LBLightGray)
            .clickable { onCheckedChange(!checked) },
        contentAlignment = Alignment.CenterStart
    ) {
        // Switch Thumb
        Box(
            modifier = Modifier
                .size(thumbSize)
                .offset(x = thumbOffset)
                .clip(CircleShape)
                .background(Color.White)
        )

        Icon(
            painter = painterResource(
                id = if (checked) R.drawable.closed_padlock else R.drawable.open_padlock
            ),
            contentDescription = description,
            modifier = Modifier
                .padding(if (checked) PaddingValues(start = 5.dp) else PaddingValues(end = 5.dp))
                .size(if (checked) 16.dp else 14.dp)
                .align(if (checked) Alignment.CenterStart else Alignment.CenterEnd),
            tint = Color.White
        )
    }
}

@Preview(widthDp = 152, heightDp = 112)
@Composable
fun ActiveAccessControlItemPreview() {
    AccessControlItem(
        mediumText = "Perfil da criança",
        smallText = "Bloqueia o acesso para editar perfil da criança.",
        imageId = R.drawable.seedling,
        isChecked = true,
        onSwitchChange = {}
    )
}

@Preview(widthDp = 152, heightDp = 112)
@Composable
fun InactiveAccessControlItemPreview() {
    AccessControlItem(
        mediumText = "Perfil da criança",
        smallText = "Bloqueia o acesso para editar perfil da criança.",
        imageId = R.drawable.seedling,
        isChecked = false,
        onSwitchChange = {}
    )
}
