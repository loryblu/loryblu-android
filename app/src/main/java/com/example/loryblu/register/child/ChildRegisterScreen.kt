package com.example.loryblu.register.child

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loryblu.R
import com.example.loryblu.ui.components.LBBoyButton
import com.example.loryblu.ui.components.LBButton
import com.example.loryblu.ui.components.LBDatePicker
import com.example.loryblu.ui.components.LBGirlButton
import com.example.loryblu.ui.components.LBRadioButton
import com.example.loryblu.ui.components.LBTitle
import com.example.loryblu.util.P_SMALL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildRegisterScreen(
    viewModel: ChildRegisterViewModel,
    navigateToHomeScreen: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        LBTitle(textRes = R.string.child_registration)

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(P_SMALL)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = uiState.name,
                onValueChange = viewModel::updateName,
                label = { Text(text = stringResource(R.string.name)) },
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.ic_user),
                        contentDescription = stringResource(
                            R.string.person_icon
                        )
                    )
                },
                modifier = Modifier.width(352.dp),
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LBDatePicker(
                labelRes = stringResource(id = R.string.birthday),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LBBoyButton(
                onClick = {
                    viewModel.updateBoyButtonState(!uiState.isBoyButtonClicked)
                    viewModel.updateGirlButtonState(false)
                }, modifier = Modifier.padding(end = 8.dp), isClicked = uiState.isBoyButtonClicked
            )

            LBGirlButton(
                onClick = {
                    viewModel.updateGirlButtonState(!uiState.isGirlButtonClicked)
                    viewModel.updateBoyButtonState(false)
                },
                modifier = Modifier.padding(start = 8.dp),
                isClicked = uiState.isGirlButtonClicked
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            LBRadioButton(
                isChecked = uiState.privacyPolicyButtonState,
                onCheckedChange = viewModel::updatePrivacyPolicyButtonState,
                modifier = Modifier
            )
            Text(
                text = stringResource(R.string.i_agree_with_the),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.LightGray,
            )
            Text(
                text = stringResource(R.string.privacy_policy),
                textDecoration = TextDecoration.Underline,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.LightGray,
                modifier = Modifier
                    .clickable { /*TODO*/ }
                    .padding(start = 4.dp),
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        LBButton(
            textRes = R.string.sign_up, onClick = {
                navigateToHomeScreen()
            }, modifier = Modifier
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
@Preview
fun PreviewComposable() {
    ChildRegisterScreen(viewModel = ChildRegisterViewModel(), navigateToHomeScreen = {}

    )
}
