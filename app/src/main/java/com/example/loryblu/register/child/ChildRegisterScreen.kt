package com.example.loryblu.register.child

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loryblu.R
import com.example.loryblu.register.guardian.GuardianRegisterViewModel
import com.example.loryblu.ui.components.LBButton
import com.example.loryblu.ui.components.LBBoyButton
import com.example.loryblu.ui.components.LBGirlButton
import com.example.loryblu.ui.components.LBTitle
import com.example.loryblu.util.P_SMALL

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChildRegisterScreen(
    viewModel: GuardianRegisterViewModel,
    navigateToChildRegister: () -> Unit
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

            OutlinedTextField(
                value = uiState.email,
                onValueChange = viewModel::updateEmail,
                label = { Text(text = stringResource(R.string.birthday)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_birthday_cake),
                        contentDescription = stringResource(
                            R.string.email_icon
                        )
                    )
                },
                modifier = Modifier.width(352.dp),
                shape = RoundedCornerShape(10.dp),
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // TODO: Botão de escolha de genero, menino ou menina
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LBBoyButton(
                textRes = R.string.boy,
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(end = 8.dp)
            )

            LBGirlButton(
                textRes = R.string.girl,
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // TODO: Radio button para concordar com a política de privacidade

        LBButton(
            textRes = R.string.sign_up,
            onClick = {
                navigateToChildRegister()
            },
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
@Preview
fun PreviewComposable() {
    ChildRegisterScreen(
        viewModel = GuardianRegisterViewModel(),
        navigateToChildRegister = {
        }

    )
}
