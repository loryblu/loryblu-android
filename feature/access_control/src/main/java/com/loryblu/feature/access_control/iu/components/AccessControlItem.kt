package com.loryblu.feature.access_control.iu.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBSoftBlue
import com.loryblu.core.ui.theme.inter
import com.loryblu.core.ui.theme.interLight
import com.loryblu.feature.access_control.R

@Composable
fun AccessControlItem(
    smallText: String = "",
    mediumText: String,
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AccessImage(
                imageId = imageId,
                contentDescription = "Access Control"
            )
            Row {
                Switch(
                    checked = isChecked,
                    modifier = Modifier.height(32.dp).width(56.dp),
                    onCheckedChange = { isChecked ->
                        onSwitchChange(isChecked)
                    }
                )
            }
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

@Preview(widthDp = 152, heightDp = 112)
@Composable
fun AccessControlComponentPreview() {
    AccessControlItem(
        smallText = "Perfil da criança",
        mediumText = "Bloqueia o acesso para editar perfil da criança.",
        imageId = R.drawable.seedling,
        isChecked = true,
        onSwitchChange = {}
    )
}
