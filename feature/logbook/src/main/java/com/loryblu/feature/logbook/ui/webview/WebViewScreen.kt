package com.loryblu.feature.logbook.ui.webview

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.loryblu.core.ui.theme.LBMediumGray
import com.loryblu.core.ui.theme.LBShadowGray
import com.loryblu.core.ui.theme.inter
import com.loryblu.feature.home.R

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(url: String, title: String, onCloseClick: () -> Unit) {
    Scaffold(
        topBar = { TopBar(title, onCloseClick) }
    ) { padding ->
        AndroidView(
            factory = {
                WebView(it).apply {
                    this.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            },
            update = { it.loadUrl(url) },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun TopBar(title: String, onCloseClick: () -> Unit) {
    Column {
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = LBShadowGray,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center),
                fontFamily = inter,
            )
            Icon(
                Icons.Filled.Close,
                contentDescription = stringResource(id = R.string.close),
                tint = LBMediumGray,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { onCloseClick.invoke() },
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
    }
}
