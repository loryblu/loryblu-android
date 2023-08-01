//package com.example.loryblu.createpassword
//
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import com.example.loryblu.R
//
//@Composable
//fun CreatePasswordScreen(
//    createPasswordViewModel: CreatePasswordViewModel
//) {
//    OutlinedTextField(
//        value = uiState.password,
//        onValueChange = { newPassword: String ->
//            viewModel.updatePassword(newPassword = newPassword)
//            viewModel.passwordCheck(newPassword = newPassword)
//        },
//        label = {
//            Text(text = stringResource(R.string.password))
//        },
//        leadingIcon = {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_lock),
//                contentDescription = stringResource(R.string.lock_icon)
//            )
//        },
//        trailingIcon = {
//            val painterIcon =
//                if (uiState.showPassword)
//                    painterResource(id = R.drawable.ic_eye_close)
//                else
//                    painterResource(id = R.drawable.ic_eye_open)
//
//            val contentDescription =
//                if (uiState.showPassword)
//                    stringResource(R.string.close_eye)
//                else
//                    stringResource(R.string.open_eye)
//
//            IconButton(onClick = { viewModel.togglePassword() }) {
//                Icon(
//                    painter = painterIcon,
//                    contentDescription = contentDescription
//                )
//            }
//        },
//        modifier = Modifier.fillMaxWidth()
//    )
//}