package com.loryblu.feature.logbook.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.components.LBTopAppBar
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.core.ui.theme.White
import com.loryblu.core.ui.utils.LBPreview
import com.loryblu.core.util.validators.InputValid
import com.loryblu.feature.home.R
import com.loryblu.feature.logbook.ui.components.LBGenderButtonProfiles
import com.loryblu.feature.logbook.ui.components.LBTextFieldProfiles
import com.loryblu.feature.logbook.ui.components.ProfilePictureComponent

@Composable
fun ChildrenProfileScreen() {
    ChildrenProfileContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChildrenProfileContent(
    onBackButtonClicked: () -> Unit = {},
    isEditable: Boolean = true,
    isBoySelected: Boolean = true,
    onEditClicked: () -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            LBTopAppBar(
                title = stringResource(R.string.appbar_title_children_profile),
                onBackClicked = { onBackButtonClicked() },
                onCloseClicked = { onBackButtonClicked() },
                showCloseButton = true
            )
        },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                ProfilePictureComponent(isEditable, onEditClicked)
                Spacer(modifier = Modifier.height(76.dp))
                UserInformation(isEditable)
                Spacer(modifier = Modifier.height(32.dp))
                GenderInformation(isEditable, isBoySelected)
                if (isEditable) {
                    Spacer(modifier = Modifier.weight(1f))
                    SaveAndCancelButton()
                }
            }
        }
    )
}

@Composable
fun UserInformation(
    isEditable: Boolean,
) {

    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        TextFieldWithTitle(
            title = stringResource(R.string.children_name)
        ) {
            LBTextFieldProfiles(
                onValueChange = {},
                placeholderRes = "Nome",
                value = "",
                icon = com.loryblu.core.ui.R.drawable.ic_user,
                error = InputValid.Valid,
                fieldFocus = {},
                isEditable = isEditable,
            )
        }

        TextFieldWithTitle(
            title = stringResource(com.loryblu.core.ui.R.string.birthday)
        ) {
            LBTextFieldProfiles(
                onValueChange = {},
                placeholderRes = "Data de nascimento",
                value = "",
                icon = com.loryblu.core.ui.R.drawable.ic_user,
                error = InputValid.Valid,
                fieldFocus = {},
                isEditable = isEditable,
            )
        }
    }
}

@Composable
fun TextFieldWithTitle(
    title: String,
    textField: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            color = LBShadowGray,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        textField()
    }

}

@Composable
fun GenderInformation(
    isEditable: Boolean,
    isBoySelected: Boolean,
) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
    ) {
        Text(
            text = stringResource(R.string.children_gender),
            color = LBShadowGray,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LBGenderButtonProfiles(
                isSelected = isBoySelected,
                isEditable = isEditable,
                onClick = {},
                iconRes = com.loryblu.core.ui.R.drawable.ic_boy,
                textRes = com.loryblu.core.ui.R.string.boy,
            )
            LBGenderButtonProfiles(
                isSelected = !isBoySelected,
                isEditable = isEditable,
                onClick = {},
                iconRes = com.loryblu.core.ui.R.drawable.ic_girl,
                textRes = com.loryblu.core.ui.R.string.girl,
            )
        }
    }
}

@Composable
fun SaveAndCancelButton() {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f),
            border = BorderStroke(2.dp, LBSkyBlue),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.cancel),
                color = LBSkyBlue,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Button(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = LBSkyBlue
            ),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(
                text = stringResource(R.string.save),
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@LBPreview
@Composable
private fun ChildrenProfilePreview() {
    ChildrenProfileContent(isEditable = false, isBoySelected = true)
}

@LBPreview
@Composable
private fun ChildrenProfileEditablePreview() {
    ChildrenProfileContent(isEditable = true, isBoySelected = true)
}
