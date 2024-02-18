package com.loryblu.feature.logbook.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loryblu.core.network.model.ApiResponseWithData
import com.loryblu.data.logbook.remote.model.LogbookTask
import com.loryblu.feature.logbook.useCases.GetUserTaskByDayOfWeek
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogbookHomeViewModel(
    private val getUserTaskByDayOfWeek: GetUserTaskByDayOfWeek
): ViewModel() {

    private val _userTasks = MutableStateFlow<ApiResponseWithData<List<LogbookTask>>>(ApiResponseWithData.Default())
    val userTasks: StateFlow<ApiResponseWithData<List<LogbookTask>>> = _userTasks

    fun selectADayOfWeek(dayOfWeekInt: Int) = viewModelScope.launch {
        val nameOfWeekDays = arrayOf("sun", "mon", "tue", "wed", "thu", "fri", "sat")
        val dayOfWeek = nameOfWeekDays[dayOfWeekInt + 1]

        getUserTaskByDayOfWeek.invoke(dayOfWeek).collect {
            _userTasks.value = it
        }
    }
}
