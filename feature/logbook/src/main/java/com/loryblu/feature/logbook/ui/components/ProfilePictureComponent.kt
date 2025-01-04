package com.loryblu.feature.logbook.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.components.CircleImage
import com.loryblu.core.ui.components.buttons.LBIconButton
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.core.ui.theme.LBSoftBlue

@Composable
fun ProfilePictureComponent(
    isEditable: Boolean = false,
    onEditClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LBSoftBlue)
            .padding(vertical = 16.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isEditable) {
            EditableProfilePicture(onEditClicked)
        } else {
            ProfilePicture()
        }
    }
}

@Composable
internal fun EditableProfilePicture(
    onEditClicked: () -> Unit = {}
) {
    Box(contentAlignment = Alignment.BottomEnd) {
        CircleImage(R.drawable.ic_launcher_foreground, White)

        LBIconButton(
            onClick = onEditClicked,
            modifier = Modifier
                .background(LBSkyBlue, CircleShape)
                .size(32.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = White
            )
        }
    }
}

@Composable
internal fun ProfilePicture() {
    CircleImage(R.drawable.ic_launcher_foreground, White)
}

@Preview
@Composable
private fun ProfilePicturePreview() {
    ProfilePictureComponent()
}

@Preview
@Composable
private fun ProfilePicturePreview2() {
    ProfilePicture()
}

@Preview
@Composable
private fun EditableProfilePicturePreview() {
    EditableProfilePicture()
}