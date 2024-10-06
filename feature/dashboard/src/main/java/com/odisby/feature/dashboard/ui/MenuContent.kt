package com.odisby.feature.dashboard.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBLightPink
import com.loryblu.core.ui.theme.LBMediumGray
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.core.ui.theme.LBSilverGray
import com.loryblu.core.ui.theme.LBSoftBlue
import com.odisby.feature.dashboard.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun MenuContent(
    menuIsOpen: Boolean,
    childFullName: String,
    parentFullName: String,
    onCloseMenu: () -> Unit,
    onExitApp: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(menuIsOpen) {
        if (menuIsOpen) state.show()
    }

    val onCloseClick: () -> Unit = {
        scope.launch {
            state.hide()
            delay(300) // to await close animation
            onCloseMenu.invoke()
        }
    }

    if (menuIsOpen) {
        val scrollState = rememberScrollState()
        ModalBottomSheet(
            sheetState = state,
            onDismissRequest = onCloseClick,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                MenuHeader(onCloseClick = onCloseClick)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    MenuSection(R.string.profile) {
                        MenuItem(
                            smallText = stringResource(id = R.string.child_name),
                            mediumText = childFullName,
                            imageId = R.drawable.seedling,
                            mediumTextMaxLines = 1,
                            mediumTextSize = 20.sp,
                            mediumTextOverflow = TextOverflow.Ellipsis,
                            onClick = {},
                        )
                        MenuItem(
                            smallText = stringResource(id = R.string.parent_name),
                            mediumText = parentFullName,
                            imageId = R.drawable.tree,
                            mediumTextMaxLines = 1,
                            mediumTextSize = 20.sp,
                            mediumTextOverflow = TextOverflow.Ellipsis,
                            onClick = {},
                        )
                    }
                    MenuSection(R.string.configurations) {
                        MenuItem(
                            smallText = stringResource(id = R.string.access_control),
                            mediumText = stringResource(id = R.string.security),
                            isReverseTextOrder = true,
                            imageId = R.drawable.shield,
                            onClick = {},
                        )
                        MenuItem(
                            mediumText = stringResource(id = R.string.faq),
                            imageId = R.drawable.question_mark,
                            onClick = {},
                        )
                        MenuItem(
                            mediumText = stringResource(id = R.string.term_and_privacy),
                            imageId = R.drawable.clipboard,
                            onClick = {},
                        )
                        MenuItem(
                            mediumText = stringResource(id = R.string.exit_app),
                            backgroundColor = LBLightPink,
                            paddingTop = 24.dp,
                            hasArrowRight = false,
                            imageId = R.drawable.exit_door,
                            onClick = onExitApp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MenuHeader(onCloseClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.menu),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = LBShadowGray,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(
            Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_menu),
            tint = LBMediumGray,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterEnd)
                .clickable { onCloseClick.invoke() },
        )
    }
}

@Composable
fun MenuSection(@StringRes titleId: Int, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(top = 24.dp, bottom = 0.dp)) {
        Text(
            text = stringResource(id = titleId),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = LBMediumGray,
        )
        Column {
            content.invoke()
        }
    }
}

@Composable
fun MenuItem(
    smallText: String = "",
    mediumText: String,
    @DrawableRes imageId: Int,
    isReverseTextOrder: Boolean = false,
    backgroundColor: Color = LBSoftBlue,
    paddingTop: Dp = 16.dp,
    hasArrowRight: Boolean = true,
    mediumTextSize: TextUnit = 22.sp,
    mediumTextMaxLines: Int = Int.MAX_VALUE,
    mediumTextOverflow: TextOverflow = TextOverflow.Clip,
    onClick: () -> Unit,
) {
    Spacer(modifier = Modifier.padding(top = paddingTop))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .border(1.dp, LBLightGray, shape = RoundedCornerShape(12.dp))
            .clickable { onClick.invoke() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MenuImage(imageId, smallText)
            Column(
                Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (isReverseTextOrder) {
                    MenuMediumText(
                        text = mediumText,
                        maxLines = mediumTextMaxLines,
                        fontSize = mediumTextSize,
                        overflow = mediumTextOverflow
                    )
                    MenuSmallText(smallText)
                } else {
                    MenuSmallText(smallText)
                    MenuMediumText(
                        text = mediumText,
                        maxLines = mediumTextMaxLines,
                        fontSize = mediumTextSize,
                        overflow = mediumTextOverflow
                    )
                }
            }

        }
        if (hasArrowRight) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = LBSilverGray,
            )
        }
    }
}

@Composable
fun MenuImage(@DrawableRes imageId: Int, contentDescription: String) {
    Surface(
        modifier = Modifier
            .size(45.dp)
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
fun MenuSmallText(text: String, modifier: Modifier = Modifier) {
    if (text.isNotEmpty()) {
        Text(
            text = text,
            color = Color.Gray,
            style = MaterialTheme.typography.labelLarge,
            modifier = modifier,
        )
    }
}

@Composable
fun MenuMediumText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 22.sp,
    maxLines: Int,
    overflow: TextOverflow
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Medium,
        fontSize = fontSize,
        color = LBShadowGray,
        maxLines = maxLines,
        overflow = overflow,
        modifier = modifier
    )
}

@Preview
@Composable
fun MenuItemPreview() {
    MenuItem(
        smallText = "Nome da criança",
        mediumText = "Maria da Silva",
        imageId = R.drawable.seedling,
        onClick = {}
    )
}
