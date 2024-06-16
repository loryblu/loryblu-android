package com.loryblu.feature.logbook.ui.task.delete

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

private class MutableDialogStateImpl<T>(initialData: T) : MutableDialogState<T> {

    private var _dialogData = mutableStateOf(initialData)

    override val dialogData: State<T>
        get() = _dialogData

    private var _isVisible = mutableStateOf(false)

    override val isVisible: State<Boolean>
        get() = _isVisible

    override fun showDialog(data: T) {
        _dialogData.value = data
        _isVisible.value = true
    }

    override fun hideDialog() {
        _isVisible.value = false
    }
}

fun <T> mutableDialogStateOf(initialData: T): MutableDialogState<T> {
    return MutableDialogStateImpl(initialData)
}

@Composable
fun <T> rememberMutableDialogState(initialData: T) = remember {
    mutableDialogStateOf(initialData)
}