package com.loryblu.feature.access_control.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class AccessItem(
    val idCard: Int,
    @StringRes val titleId: Int,
    @StringRes val descriptionId: Int,
    @DrawableRes val imageId: Int,
    val isDisabled: Boolean = false
)
