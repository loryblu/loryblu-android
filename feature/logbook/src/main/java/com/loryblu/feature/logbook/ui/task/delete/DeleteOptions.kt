package com.loryblu.feature.logbook.ui.task.delete

sealed class DeleteOption {
    data class SingleDay(val day: Int): DeleteOption()
    data object Everyday: DeleteOption()
}