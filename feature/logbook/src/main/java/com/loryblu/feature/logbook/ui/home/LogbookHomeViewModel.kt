package com.loryblu.feature.logbook.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.local.ShiftItem
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.logbook.useCases.GetUserTaskByDayOfWeek
import com.loryblu.feature.logbook.utils.intToDayOfWeek
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogbookHomeViewModel(
    private val getUserTaskByDayOfWeek: GetUserTaskByDayOfWeek
) : ViewModel() {

    private val _userTasks =
        MutableStateFlow<ApiResponseWithData<List<LogbookTask>>>(ApiResponseWithData.Default())
    val userTasks: StateFlow<ApiResponseWithData<List<LogbookTask>>> = _userTasks

    var lastDayOfWeek = 0
    var lastShift = 0

    fun selectADayOfWeek(dayOfWeekInt: Int, shift: Int, force: Boolean = false) =
        viewModelScope.launch {
            lastDayOfWeek = dayOfWeekInt
            lastShift = shift

            getUserTaskByDayOfWeek.invoke(
                dayOfWeek = intToDayOfWeek(dayOfWeekInt),
                shift = shift,
                force = force
            ).collect {
                _userTasks.value = it
            }
        }
}
