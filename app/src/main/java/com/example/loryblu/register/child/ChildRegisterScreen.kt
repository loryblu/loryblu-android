package com.example.loryblu.register.child

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loryblu.R
import com.example.loryblu.ui.components.LBBoyButton
import com.example.loryblu.ui.components.LBButton
import com.example.loryblu.ui.components.LBDatePicker
import com.example.loryblu.ui.components.LBGirlButton
import com.example.loryblu.ui.components.LBNameTextField
import com.example.loryblu.ui.components.LBRadioButton
import com.example.loryblu.ui.components.LBTitle
import com.example.loryblu.util.P_SMALL
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildRegisterScreen(
    viewModel: ChildRegisterViewModel,
    navigateToHomeScreen: () -> Unit,
    onSignUpButtonClicked: () -> Unit,
    shouldGoToNextScreen: Boolean,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

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

            LBNameTextField(
                value = uiState.name,
                onValueChange = { name: String ->
                    viewModel.updateName(name)
                    viewModel.nameState()
                },
                labelRes = stringResource(id = R.string.name),
                error = uiState.nameState,
            )

            LBDatePicker(
                labelRes = stringResource(id = R.string.birthday),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

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
            textRes = R.string.sign_up,
            onClick = {
                onSignUpButtonClicked()
            },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(32.dp))
    }

    LaunchedEffect(key1 = shouldGoToNextScreen) {
        if (shouldGoToNextScreen) {
            coroutineScope.launch {
                showToast(context, "Sign up successful! Logging in...")
                navigateToHomeScreen()
            }
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
@Preview
fun PreviewComposable() {
    ChildRegisterScreen(
        viewModel = ChildRegisterViewModel(),
        navigateToHomeScreen = {},
        onSignUpButtonClicked = {},
        shouldGoToNextScreen = false,
    )
}
