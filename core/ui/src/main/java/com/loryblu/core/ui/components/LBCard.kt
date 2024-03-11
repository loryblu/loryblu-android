package com.loryblu.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBAfternoonBlue
import com.loryblu.core.ui.theme.LBBottomDisabledColor
import com.loryblu.core.ui.theme.LBBottomHome
import com.loryblu.core.ui.theme.LBCardSoftBlue
import com.loryblu.core.ui.theme.LBContentHome
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBDisabledGray
import com.loryblu.core.ui.theme.LBNightBlue
import com.loryblu.core.ui.theme.LoryBluTheme
import com.loryblu.data.logbook.local.CategoryItem
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.local.TaskItem
import com.odisby.data.dashboard.local.DashboardItem
import com.odisby.data.dashboard.local.getAllDashboardItems

@Composable
fun LBCardDashboard(
    card: DashboardItem,
    modifier: Modifier,
    onclick: () -> Unit,
) {
    val contentColor = LBContentHome
    val bottomColor = LBBottomHome
    val borderColor = LBBottomHome
    val rounded = 5
    val saturation = if (card.isDisabled) .5f else 1f

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = contentColor,
        ),
        border = BorderStroke(4.dp, borderColor),
        modifier = modifier
            .clickable {
                if (!card.isDisabled) {
                    onclick()
                }
            }
            .alpha(saturation),
        shape = RoundedCornerShape(rounded),
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
                    .alpha(saturation),
                painter = painterResource(id = card.drawable),
                contentDescription = stringResource(id = card.text),
                contentScale = ContentScale.Fit,
            )
        }
        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize()
                .background(color = bottomColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = card.text),
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}

@Composable
fun LBCategoryCard(
    card: CategoryItem,
    modifier: Modifier,
    selected: Boolean,
    onclick: () -> Unit,
) {
    val contentColor = LBCardSoftBlue
    val bottomColor = LBDarkBlue
    val borderColor = LBDarkBlue
    val rounded = 5
    val saturation = if (selected) 1f else .6f

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = contentColor,
        ),
        border = if(selected) BorderStroke(4.dp, borderColor) else BorderStroke(4.dp, Color.Transparent),
        modifier = modifier
            .clickable {
                if (!card.isDisabled) {
                    onclick()
                }
            }
            .alpha(saturation),
        shape = RoundedCornerShape(rounded),
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
                    .alpha(saturation),
                painter = painterResource(id = card.drawable),
                contentDescription = stringResource(id = card.text),
                contentScale = ContentScale.Fit,
            )
        }
        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize()
                .background(color = bottomColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = card.text),
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}

@Composable
fun LBTaskCard(
    card: TaskItem,
    modifier: Modifier,
    selected: Boolean,
    onclick: () -> Unit,
) {
    val contentColor = LBCardSoftBlue
    val bottomColor = LBDarkBlue
    val borderColor = LBDarkBlue
    val rounded = 5

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = contentColor,
        ),
        border = if(selected) BorderStroke(4.dp, borderColor) else BorderStroke(4.dp, Color.Transparent),
        modifier = modifier
            .clickable {
                onclick()
            }
            .alpha(1f),
        shape = RoundedCornerShape(rounded),
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .alpha(1f),
                painter = painterResource(id = card.drawable),
                contentDescription = stringResource(id = card.text),
                contentScale = ContentScale.Fit,
            )
        }
        Column(
            modifier = Modifier
                .weight(.4f)
                .fillMaxSize()
                .background(color = bottomColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = card.text),
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}


@Composable
fun LBCardShift(
    card: ShiftItem,
    modifier: Modifier,
    clicked: Boolean,
    onclick: () -> Unit,
) {
    var contentColor = LBCardSoftBlue
    var bottomColor = LBDarkBlue
    val borderColor = LBDarkBlue
    var saturation = 1f
    val rounded = 10
    var imageWidth = 0
    var imageHeight = 0

    if (clicked) {
        when (card) {
            is ShiftItem.Morning -> {
                contentColor = LBCardSoftBlue
                bottomColor = LBDarkBlue
                imageHeight = 102
                imageWidth = 96
            }

            is ShiftItem.Afternoon -> {
                contentColor = LBAfternoonBlue
                bottomColor = LBDarkBlue
                imageHeight = 42
                imageWidth = 54
            }

            is ShiftItem.Night -> {
                contentColor = LBNightBlue
                bottomColor = LBDarkBlue
                imageHeight = 52
                imageWidth = 32
            }
            else -> { }
        }
    } else {
        when (card) {
            is ShiftItem.Morning -> {
                saturation = 1f
                contentColor = LBDisabledGray
                bottomColor = LBBottomDisabledColor
                imageHeight = 102
                imageWidth = 96
            }

            is ShiftItem.Afternoon -> {
                saturation = 1f
                contentColor = LBDisabledGray
                bottomColor = LBBottomDisabledColor
                imageHeight = 42
                imageWidth = 54
            }

            is ShiftItem.Night -> {
                saturation = 1f
                contentColor = LBDisabledGray
                bottomColor = LBBottomDisabledColor
                imageHeight = 52
                imageWidth = 32
            }
            else -> { }
        }
    }

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = contentColor,
        ),
        border =
        if (clicked) {
            BorderStroke(4.dp, borderColor)
        } else {
            BorderStroke(Dp.Unspecified, borderColor)
        },
        modifier = modifier
            .clickable {
                onclick()
            }
            .alpha(saturation),
        shape = RoundedCornerShape(rounded),
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .padding(8.dp)
                    .width(imageWidth.dp)
                    .height(imageHeight.dp),
                painter = painterResource(id = card.drawable),
                contentDescription = stringResource(id = card.text),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.colorMatrix(
                    colorMatrix = ColorMatrix().apply {
                        setToSaturation(
                            saturation
                        )
                    }
                )
            )
        }
        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize()
                .background(
                    color =
                    bottomColor
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = card.text),
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun LBDashboardCardPreview() {
    LoryBluTheme {
        LBCardDashboard(
            getAllDashboardItems()[0],
            modifier = Modifier.size(250.dp),
            onclick = {}
        )
    }
}

@Preview(showBackground = false)
@Composable
fun LBLogbookCardPreview() {
    LoryBluTheme {
        LBCategoryCard(
            CategoryItem.getAllCategory()[0],
            modifier = Modifier.size(250.dp),
            selected = true,
            onclick = {}
        )
    }
}

@Preview(showBackground = false)
@Composable
fun LBShiftCardPreview() {
    LoryBluTheme {
        LBCardShift(
            ShiftItem.getShiftItems()[0],
            modifier = Modifier.size(250.dp),
            clicked = false,
            onclick = {}
        )
    }
}