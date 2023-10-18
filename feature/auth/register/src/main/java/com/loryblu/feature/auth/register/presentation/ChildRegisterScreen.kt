package com.loryblu.feature.auth.register.presentation

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loryblu.core.ui.R
import com.loryblu.core.ui.components.LBButton
import com.loryblu.core.ui.components.LBDatePicker
import com.loryblu.core.ui.components.LBGenderButton
import com.loryblu.core.ui.components.LBNameTextField
import com.loryblu.core.ui.components.LBRadioButton
import com.loryblu.core.ui.components.LBTitle
import com.loryblu.core.ui.models.GenderInput
import com.loryblu.core.ui.theme.LBDarkBlue
import com.loryblu.core.ui.theme.LBErrorColor
import com.loryblu.core.ui.theme.LBLightGray
import com.loryblu.core.ui.theme.LBSoftGray
import com.loryblu.core.ui.theme.LBSilverGray
import com.loryblu.core.ui.theme.LBSkyBlue
import com.loryblu.core.util.extensions.toApiFormat
import com.loryblu.core.util.validators.BirthdayInputValid
import com.loryblu.core.util.validators.NameInputValid
import com.loryblu.feature.auth.register.model.Children

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildRegisterScreen(
    navigateToConfirmationScreen: () -> Unit,
    onSignUpButtonClicked: (Children) -> Unit,
    shouldGoToNextScreen: Boolean,
    nameStateValidation: (name: String) -> NameInputValid,
    birthdayStateValidation: (birthday: String) -> BirthdayInputValid,
    genderStateValidation: (gender: GenderInput) -> GenderInput,
    intentForPrivacy: Intent,
    apiErrorMessage: List<String>,
) {

    var privacy by rememberSaveable { mutableStateOf(false) }
    var privacyState by rememberSaveable { mutableStateOf(true) }
    var name by rememberSaveable { mutableStateOf("") }
    var nameState by rememberSaveable { mutableStateOf<NameInputValid>(NameInputValid.Empty) }
    var birthday by rememberSaveable { mutableStateOf("") }
    var birthdayState by rememberSaveable { mutableStateOf<BirthdayInputValid>(BirthdayInputValid.Empty) }
    var gender by rememberSaveable { mutableStateOf<GenderInput>(GenderInput.Empty) }
    var isNameFieldFocused by remember { mutableStateOf(false) }
    var isBirthdayFieldFocused by remember { mutableStateOf(false) }

    val boyButtonSelected = (gender is GenderInput.MALE)
    val girlButtonSelected = (gender is GenderInput.FEMALE)
    val genderButtonError = (gender is GenderInput.Error)

    val (
        boyButtonBorderColor,
        boyButtonContentIconAndTextColor,
        boyButtonContainerColor
    ) = listOf(
        when {
            boyButtonSelected -> LBDarkBlue
            genderButtonError -> LBErrorColor
            else -> LBSilverGray
        },
        if (boyButtonSelected) LBSoftGray else LBSilverGray,
        if (boyButtonSelected) LBDarkBlue else LBSoftGray,
    )

    val (
        girlButtonBorderColor,
        girlButtonContentIconAndTextColor,
        girlButtonContainerColor
    ) = listOf(
        when {
            girlButtonSelected -> LBDarkBlue
            genderButtonError -> LBErrorColor
            else -> LBSilverGray
        },
        if (girlButtonSelected) LBSoftGray else LBSilverGray,
        if (girlButtonSelected) LBDarkBlue else LBSoftGray,
    )

    val activityResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _ -> }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        LBTitle(textRes = R.string.child_registration)

        Spacer(modifier = Modifier.height(32.dp))

        LBNameTextField(
            value = name,
            onValueChange = { newName: String ->
                name = newName
                nameState = nameStateValidation(name)
            },
            placeholderRes = stringResource(id = R.string.full_name),
            error = nameState,
            fieldFocus = { isNameFieldFocused = it }
        )

        if (nameState is NameInputValid.Error && isNameFieldFocused) {
            val nameError = nameState as NameInputValid.Error

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp)
            ) {
                Text(
                    fontSize = 14.sp,
                    modifier = Modifier,
                    text = stringResource(id = nameError.messageId),
                    fontWeight = FontWeight.Bold,
                    color = LBErrorColor
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LBDatePicker(
            placeholderRes = stringResource(id = R.string.birthday),
            error = birthdayState,
            onBirthdayChange = { newBirthday ->
                birthday = newBirthday
                birthdayState = birthdayStateValidation(newBirthday)
            },
            fieldFocus = { isBirthdayFieldFocused = it }
        )

        if (birthdayState is BirthdayInputValid.Error && isBirthdayFieldFocused) {
            val birthdayError = birthdayState as BirthdayInputValid.Error

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp)
            ) {
                Text(
                    fontSize = 14.sp,
                    modifier = Modifier,
                    text = stringResource(id = birthdayError.messageId),
                    fontWeight = FontWeight.Bold,
                    color = LBErrorColor
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LBGenderButton(
                onClick = {
                    gender = GenderInput.MALE
                },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                painterRes = painterResource(id = R.drawable.ic_boy),
                contentDescriptionRes = stringResource(R.string.boy_icon),
                textRes = stringResource(R.string.boy),
                buttonColors = ButtonDefaults.outlinedButtonColors(
                    contentColor = boyButtonContentIconAndTextColor,
                    containerColor = boyButtonContainerColor
                ),
                borderStrokeAndColors = BorderStroke(
                    width = 2.dp,
                    color = boyButtonBorderColor
                ),
                iconColor = boyButtonContentIconAndTextColor,
                textColor = boyButtonContentIconAndTextColor
            )

            Spacer(modifier = Modifier.width(16.dp))

            LBGenderButton(
                onClick = {
                    gender = GenderInput.FEMALE
                },
                modifier = Modifier
                    .height(50.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                painterRes = painterResource(id = R.drawable.ic_girl),
                contentDescriptionRes = stringResource(R.string.girl_icon),
                textRes = stringResource(R.string.girl),
                buttonColors = ButtonDefaults.outlinedButtonColors(
                    contentColor = girlButtonContentIconAndTextColor,
                    containerColor = girlButtonContainerColor
                ),
                borderStrokeAndColors = BorderStroke(
                    width = 2.dp,
                    color = girlButtonBorderColor
                ),
                iconColor = girlButtonContentIconAndTextColor,
                textColor = girlButtonContentIconAndTextColor
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 4.dp, top = 4.dp)
        ) {
            if (gender == GenderInput.Error) {
                Text(
                    fontSize = 14.sp,
                    text = stringResource(R.string.select_a_button),
                    fontWeight = FontWeight.Bold,
                    color = LBErrorColor
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            LBRadioButton(
                isChecked = privacy,
                onCheckedChange = {
                    privacy = !privacy
                    privacyState = privacy
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = LBSilverGray,
                    unselectedColor = if (!privacyState) LBErrorColor else LBSilverGray
                )
            )

            Text(
                text = stringResource(R.string.i_agree_with_the),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (!privacyState) {
                    LBErrorColor
                } else {
                    LBSilverGray
                }
            )

            Text(
                text = stringResource(R.string.privacy_policy),
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (!privacyState) {
                    LBErrorColor
                } else {
                    LBSilverGray
                },
                modifier = Modifier
                    .clickable { activityResultLauncher.launch(intentForPrivacy) }
                    .drawBehind {
                        drawLine(
                            color = LBSilverGray,
                            strokeWidth = 1.dp.toPx(),
                            start = Offset(-1.dp.toPx(), size.height),
                            end = Offset(size.width + 2.dp.toPx(), size.height),
                            cap = StrokeCap.Round
                        )
                    }
                    .padding(start = 4.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 4.dp)
        ) {
            if (!privacyState) {
                Text(
                    fontSize = 14.sp,
                    modifier = Modifier,
                    text = stringResource(R.string.accept_privacy_policy),
                    fontWeight = FontWeight.Bold,
                    color = LBErrorColor
                )
            }
            apiErrorMessage.forEach {
                Text(
                    fontSize = 14.sp,
                    text = it,
                    color = LBErrorColor
                )
            }
        }

        Spacer(modifier = Modifier.height(44.dp))

        LBButton(
            areAllFieldsValid = nameState is NameInputValid.Valid
                    && birthdayState is BirthdayInputValid.Valid
                    && (gender == GenderInput.MALE || gender == GenderInput.FEMALE)
                    && privacyState == privacy,
            textRes = R.string.sign_up,
            onClick = {
                nameState = nameStateValidation(name)
                birthdayState = birthdayStateValidation(birthday)
                gender = genderStateValidation(gender)
                privacyState = privacy
                if (
                    nameState == NameInputValid.Valid
                    && birthdayState == BirthdayInputValid.Valid
                    && (gender == GenderInput.MALE || gender == GenderInput.FEMALE)
                    && privacyState
                ) {
                    onSignUpButtonClicked(
                        Children(
                            name = name,
                            policiesAccepted = privacy,
                            birthday = birthday.toApiFormat(),
                            gender = gender
                        )
                    )
                }
            },
            buttonColors = ButtonDefaults.buttonColors(
                disabledContainerColor = LBLightGray,
                containerColor = LBSkyBlue
            ),
            textColor = if (
                nameState == NameInputValid.Valid
                && birthdayState == BirthdayInputValid.Valid
                && (gender == GenderInput.MALE || gender == GenderInput.FEMALE)
                && privacyState == privacy
            ) LBSoftGray else LBSkyBlue
        )

        Spacer(modifier = Modifier.height(32.dp))

    }

    LaunchedEffect(key1 = shouldGoToNextScreen) {
        if (shouldGoToNextScreen) {
            navigateToConfirmationScreen()
        }
    }
}