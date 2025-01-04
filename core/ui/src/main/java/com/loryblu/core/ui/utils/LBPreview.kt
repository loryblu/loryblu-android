package com.loryblu.core.ui.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Preview(
    name = "Phone - Dark",
    device = "spec:width=411dp,height=891dp",
    showSystemUi = true,
    locale = "pt-rBR",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Phone - Light",
    device = "spec:width=411dp,height=891dp",
    showSystemUi = true,
    locale = "pt-rBR",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Phone - en-US",
    device = "spec:width=411dp,height=891dp",
    showSystemUi = true,
    locale = "en-US",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
annotation class LBPreview