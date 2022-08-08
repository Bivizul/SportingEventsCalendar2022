package com.bivizul.sportingeventscalendar2022.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.sportingeventscalendar2022.data.Repository
import com.bivizul.sportingeventscalendar2022.model.Events
import com.bivizul.sportingeventscalendar2022.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val selectionFlow = MutableStateFlow(emptyList<LocalDate>())

    private val _events = MutableStateFlow<Resource<Events>>(Resource.Success(Events(listOf())))
    val events: StateFlow<Resource<Events>> = _events.asStateFlow()

    private val _events3 = MutableStateFlow(Events(listOf()))
    val events3: StateFlow<Events> = _events3.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _events.emit(Resource.Loading())
            val response = repository.getEvents()
            if (response.isSuccessful) {
                response.body()?.let {
                    _events.emit(Resource.Success(it))
                    _events3.emit(it)
                }
            } else {
                _events.emit(Resource.Error(response.message()))
            }
        }
    }

    val selectedEventsIdFlow = _events3.combine(selectionFlow) { response, selection ->
        response.events.filter { LocalDate.parse(it.date) in selection }.sumOf { it.id }
    }

    fun onSelectionChanged(selection: List<LocalDate>) {
        selectionFlow.value = selection
    }
}
