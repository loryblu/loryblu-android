package com.loryblu.loryblu

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.compose.rememberNavController
import com.loryblu.core.ui.theme.LoryBluTheme
import com.loryblu.core.util.Screen
import com.loryblu.loryblu.navigation.SetupNavGraph
import com.loryblu.loryblu.usecases.IsUserLogged
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    private val isUserLogged: IsUserLogged by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val userLogged: Boolean

        // While this is being executed the splash screen is shown to user. No problem with NRA
        runBlocking {
            userLogged = isUserLogged()
        }

        setContent {
            LoryBluTheme {
                KoinContext {
                    val navController = rememberNavController()
                    checkNotificationPolicyAccess(notificationManager, this)
                    SetupNavGraph(
                        startDestination = if (userLogged) {
                            Screen.Dashboard.route
                        } else {
                            Screen.Login.route
                        },
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun checkNotificationPolicyAccess(
    notificationManager: NotificationManager,
    context: Context
): Boolean {
    if (notificationManager.areNotificationsEnabled() || Build.VERSION.SDK_INT < 32) {
        return true
    } else {
        PermissionDialog(context)
    }
    return false
}

@Composable
fun PermissionDialog(context: Context) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = true
            ),
            title = { Text("Permissão necessária") },
            text = {
                Text("Para utilizar o Chucker no modo de Debug é necessário ativar as notificações do aplicativo no Android 13+")
            },
            onDismissRequest = { openDialog.value = true },

            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    },
                ) {
                    Text(text = "Cancelar")
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    val uri: Uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                    val intent =
                        Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            data = uri
                        }
                    openDialog.value = false
                    startActivity(context, intent, null)
                }) { Text(text = "Confirmar") }
            },
        )
    }
}
