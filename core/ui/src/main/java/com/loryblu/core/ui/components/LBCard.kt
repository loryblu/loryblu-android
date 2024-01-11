package com.loryblu.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.loryblu.core.ui.theme.LBBottomAfternoon
import com.loryblu.core.ui.theme.LBBottomDisabledColor
import com.loryblu.core.ui.theme.LBBottomHome
import com.loryblu.core.ui.theme.LBBottomMorningColor
import com.loryblu.core.ui.theme.LBCardSoftBlue
import com.loryblu.core.ui.theme.LBContentHome
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBDisabledGray
import com.loryblu.core.ui.theme.LBNightBlue
import com.loryblu.core.ui.theme.LoryBluTheme
import com.loryblu.data.logbook.local.LogbookItem
import com.loryblu.data.logbook.R
import com.loryblu.data.logbook.local.getAllCategoryItems
import com.odisby.data.dashboard.local.getAllDashboardItems
import com.odisby.data.dashboard.local.DashboardItem


@Composable
fun LBCard(
    card: LogbookItem,
    modifier: Modifier,
    onclick: () -> Unit,
) {
    var cardClicked by rememberSaveable {
        mutableStateOf(false)
    }

    var contentColor = LBCardSoftBlue
    var bottomColor = LBDarkBlue
    var borderColor = LBDarkBlue
    var saturation = 1f
    val imgSaturation = 0.10f
    var rounded = 5

    val isShiftCard = card.text == R.string.shift_morning ||
            card.text == R.string.shift_afternoon ||
            card.text == R.string.shift_night

    val isHomeCard = card.text == com.odisby.data.dashboard.R.string.logbook ||
            card.text == com.odisby.data.dashboard.R.string.game_track ||
            card.text == com.odisby.data.dashboard.R.string.story_track

    if (isHomeCard) {
        contentColor = LBContentHome
        bottomColor = LBBottomHome
        borderColor = LBBottomHome
        saturation = 1f
    }

    if (isShiftCard)
        rounded = 10

    if (cardClicked) {
        when (card.text) {
            R.string.shift_morning -> {
                contentColor = LBCardSoftBlue
                bottomColor = LBBottomMorningColor
            }

            R.string.shift_afternoon -> {
                contentColor = LBAfternoonBlue
                bottomColor = LBBottomAfternoon
            }

            R.string.shift_night -> {
                contentColor = LBNightBlue
                bottomColor = LBDarkBlue
            }
        }
    } else {
        saturation = if ((!card.isDisabled && isHomeCard) || card.isCardTask) {
            1f
        } else {
            0.50f
        }
        if (card.isCardTask) {
            bottomColor = Color(0xFF6F9EB9)
        }
        when (card.text) {
            R.string.shift_morning -> {
                saturation = 1f
                contentColor = LBDisabledGray
                bottomColor = LBBottomDisabledColor
            }

            R.string.shift_afternoon -> {
                saturation = 1f
                contentColor = LBDisabledGray
                bottomColor = LBBottomDisabledColor
            }

            R.string.shift_night -> {
                saturation = 1f
                contentColor = LBDisabledGray
                bottomColor = LBBottomDisabledColor
            }
        }
    }

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = contentColor,
        ),
        border =
        if (cardClicked) {
            BorderStroke(4.dp, borderColor)
        } else {
            BorderStroke(Dp.Unspecified, borderColor)
        },
        modifier = modifier
            .clickable {
                if (!card.isDisabled) {
                    cardClicked = !cardClicked
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
                    .padding(0.dp),
                painter = painterResource(id = card.drawable),
                contentDescription = stringResource(id = card.text),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.colorMatrix(
                    colorMatrix = ColorMatrix().apply {
                        setToSaturation(
                            if (isHomeCard && card.isDisabled) {
                                imgSaturation
                            } else {
                                saturation
                            }
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


@Preview(showBackground = false)
@Composable
fun LBCardPreview() {
    LoryBluTheme {
        LBCard(
            getAllCategoryItems()[0],
            modifier = Modifier.size(250.dp),
            onclick = {}
        )
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