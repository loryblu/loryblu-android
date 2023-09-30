package com.example.loryblu.register.child

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
    var areButtonsClicked by remember { mutableStateOf(true) }
    var isPrivacyChecked by remember { mutableStateOf(true) }
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
                .padding(16.dp)
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
                error = uiState.birthdayState,
                onBirthdayChange = { newBirthday ->
                    viewModel.updateBirthday(newBirthday)
                },
                birthDayState = {
                    viewModel.birthdayState()
                }
            )
        }

        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LBBoyButton(
                onClick = {
                    viewModel.updateBoyButtonState(!uiState.isBoyButtonClicked)
                    viewModel.updateGirlButtonState(false)
                    areButtonsClicked = true
                },
                modifier = Modifier
                    .height(44.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                isClicked = uiState.isBoyButtonClicked,
                isBothButtonClicked = areButtonsClicked,
            )
            Spacer(modifier = Modifier.width(16.dp))
            LBGirlButton(
                onClick = {
                    viewModel.updateGirlButtonState(!uiState.isGirlButtonClicked)
                    viewModel.updateBoyButtonState(false)
                    areButtonsClicked = true
                },
                modifier = Modifier
                    .height(44.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                isClicked = uiState.isGirlButtonClicked,
                isBothButtonClicked = areButtonsClicked,
            )

        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(end = 42.dp)
        ) {
            if (!areButtonsClicked) {
                Text(
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.End),
                    text = stringResource(R.string.select_a_button),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, bottom = 0.dp, top = 0.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            LBRadioButton(
                isChecked = uiState.privacyPolicyButtonState,
                onCheckedChange = {
                    viewModel.updatePrivacyPolicyButtonState(!uiState.privacyPolicyButtonState)
                    isPrivacyChecked = true
                },
                modifier = Modifier,
            )
            Text(
                text = stringResource(R.string.i_agree_with_the),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = if (!isPrivacyChecked) {
                    MaterialTheme.colorScheme.error
                }else {
                    Color.LightGray
                }
            )
            Text(
                text = stringResource(R.string.privacy_policy),
                textDecoration = TextDecoration.Underline,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = if (!isPrivacyChecked) {
                    MaterialTheme.colorScheme.error
                }else {
                    Color.LightGray
                }
                ,
                modifier = Modifier
                    .clickable { /*TODO*/ }
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(end = 16.dp)
        ) {
            if (!isPrivacyChecked) {
                Text(
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.End),
                    text = stringResource(R.string.accept_privacy_policy),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        LBButton(
            textRes = R.string.sign_up,
            onClick = {
                if (!uiState.isBoyButtonClicked && !uiState.isGirlButtonClicked)
                    areButtonsClicked = false
                if (!uiState.privacyPolicyButtonState)
                    isPrivacyChecked = false
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
