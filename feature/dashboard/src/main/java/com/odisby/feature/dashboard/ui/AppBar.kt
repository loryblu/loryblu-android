package com.odisby.feature.dashboard.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBDisabledGray
import com.odisby.feature.dashboard.R
import com.loryblu.core.ui.components.extensions.loryShadow

@Composable
fun AppBar(childFirstName: String, onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 26.dp, bottom = 32.dp, end = 26.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.logo_home),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(95.dp)
            )
            Spacer(modifier = Modifier.width(24.dp))
            Column {
                TextBalloon(stringResource(id = R.string.hello, childFirstName))
                Spacer(modifier = Modifier.height(8.dp))
                TextBalloon(stringResource(id = R.string.how_are_you))
            }
        }
        MenuIcon(onClick = onMenuClick)
    }
}

@Composable
fun TextBalloon(text: String) {
    val corner: Dp = 13.dp
    Box(
        modifier = Modifier
            .height(40.dp)
            .widthIn(min = 50.dp, max = 170.dp)
            .clip(
                RoundedCornerShape(
                    topStart = corner,
                    topEnd = corner,
                    bottomEnd = corner,
                    bottomStart = 0.dp,
                )
            )
            .background(LBDarkBlue)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            fontSize = 17.sp,
            text = text,
            textAlign = TextAlign.Center,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun MenuIcon(onClick: () -> Unit) {
    val cornerShape = RoundedCornerShape(25)
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .loryShadow(
                offsetX = 1.dp,
                offsetY = 1.dp,
                cornerRadius = 8.dp,
            )
            .clip(cornerShape)
            .border(border = BorderStroke(1.dp, LBDisabledGray), shape = cornerShape)
            .clickable { onClick.invoke() }
            .background(Color.White, shape = cornerShape)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.menu_icon),
            contentDescription = stringResource(id = R.string.menu),
            tint = LBDisabledGray,
            modifier = Modifier.size(17.dp)
        )
    }
}
