package com.loryblu.feature.auth.register.child

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.components.LBBoyButton
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.components.LBDatePicker
import com.loryblu.core.ui.components.LBGirlButton
import com.loryblu.core.ui.components.LBNameTextField
import com.loryblu.core.ui.components.LBRadioButton
import com.loryblu.core.ui.components.LBSuccessLabel
import com.loryblu.core.ui.components.LBTitle
import com.loryblu.core.ui.models.GenderInput
import com.loryblu.core.util.validators.BirthdayInputValid
import com.loryblu.core.util.validators.NameInputValid
import com.loryblu.feature.auth.register.util.Children
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildRegisterScreen(
    viewModel: ChildRegisterViewModel,
    navigateToConfirmationScreen: () -> Unit,
    onSignUpButtonClicked: (Children) -> Unit,
    shouldGoToNextScreen: Boolean,
    intentForPrivacy: Intent,
    apiErrorMessage: String?,
) {
    val coroutineScope = rememberCoroutineScope()
    var privacy by remember { mutableStateOf(false) }
    var privacyState by remember { mutableStateOf(true) }

    var name by remember { mutableStateOf("") }
    var nameState by remember { mutableStateOf<NameInputValid>(NameInputValid.Empty) }

    var birthday by remember { mutableStateOf("") }
    var birthdayState by remember { mutableStateOf<BirthdayInputValid>(BirthdayInputValid.Empty) }

    var gender by remember { mutableStateOf<GenderInput>(GenderInput.Empty) }

    var showApiFailure by remember { mutableStateOf(false) }


    val activityResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ -> }

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
                value = name,
                onValueChange = { newName: String ->
                    name = newName
                    nameState = viewModel.nameState(newName)
                },
                labelRes = stringResource(id = R.string.name),
                error = nameState,
            )

            LBDatePicker(
                labelRes = stringResource(id = R.string.birthday),
                error = birthdayState,
                onBirthdayChange = { newBirthday ->
                    birthday = newBirthday
                    birthdayState = viewModel.birthdayState(newBirthday)
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
                    gender = GenderInput.MALE
                },
                modifier = Modifier
                    .height(44.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                genderInput = gender,
            )
            Spacer(modifier = Modifier.width(16.dp))
            LBGirlButton(
                onClick = {
                    gender = GenderInput.FEMALE
                },
                modifier = Modifier
                    .height(44.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                genderInput = gender,
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
            if (gender == GenderInput.Error) {
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
                isChecked = privacy,
                onCheckedChange = {
                    privacy = !privacy
                    privacyState = viewModel.privacyState(privacy)
                },
                modifier = Modifier,
            )
            Text(
                text = stringResource(R.string.i_agree_with_the),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = if (privacyState) {
                    Color.LightGray
                }else {
                    MaterialTheme.colorScheme.error
                }
            )
            TextButton(
                onClick = {
                    activityResultLauncher.launch(intentForPrivacy)
                },
            ) {
                Text(
                    text = stringResource(R.string.privacy_policy),
                    textDecoration = TextDecoration.Underline,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = if (privacyState) {
                        Color.LightGray
                    }else {
                        MaterialTheme.colorScheme.error
                    }
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(end = 16.dp)
        ) {
            if (!privacyState) {
                Text(
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.End),
                    text = stringResource(R.string.accept_privacy_policy),
                    color = MaterialTheme.colorScheme.error
                )
            }
            if (showApiFailure) {
                LBSuccessLabel(labelRes = "Ocorreu algum erro.")
            }
        }


        Spacer(modifier = Modifier.height(32.dp))

        LBButton(
            textRes = R.string.sign_up,
            onClick = {
                nameState = viewModel.nameState(name)
                birthdayState = viewModel.birthdayState(birthday)
                gender = viewModel.genderState(gender)
                privacyState = viewModel.privacyState(privacy)

                if(
                    nameState == NameInputValid.Valid
                    && birthdayState == BirthdayInputValid.Valid
                    && (gender == GenderInput.MALE || gender == GenderInput.FEMALE)
                    && privacyState
                    ) {
                    onSignUpButtonClicked(
                        Children(
                            name = name,
                            policiesAccepted = privacy,
                            birthday = birthday,
                            gender = gender
                        )
                    )
                }
            },
            modifier = Modifier
        )

        Spacer(modifier = Modifier.height(32.dp))
    }

    LaunchedEffect(key1 = shouldGoToNextScreen) {
        if (shouldGoToNextScreen) {
            coroutineScope.launch {
                navigateToConfirmationScreen()
            }
        }
    }

    LaunchedEffect(key1 = apiErrorMessage) {
        if (apiErrorMessage != null) {
            showApiFailure = true
        }
    }
}




//@Composable
//@Preview
//fun PreviewComposable() {
//    ChildRegisterScreen(
//        viewModel = ChildRegisterViewModel(),
//        navigateToConfirmationScreen = {},
//        onSignUpButtonClicked = {},
//        shouldGoToNextScreen = false,
//        intentForPrivacy = Intent(Intent.ACTION_VIEW),
//    )
//}
