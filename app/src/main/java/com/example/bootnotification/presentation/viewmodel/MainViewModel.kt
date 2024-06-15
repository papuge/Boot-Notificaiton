package com.example.bootnotification.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootnotification.domain.model.BootEvent
import com.example.bootnotification.domain.usecase.GetBootEventsUseCase
import com.example.bootnotification.domain.usecase.SaveBootEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBootEventsUseCase: GetBootEventsUseCase,
    private val saveBootEventUseCase: SaveBootEventUseCase
) : ViewModel() {

    private val _bootEvents = MutableStateFlow<List<BootEvent>>(emptyList())
    val bootEvents: StateFlow<List<BootEvent>> = _bootEvents.asStateFlow()

    init {
        fetchBootEvents()
    }

    fun fetchBootEvents() {
        viewModelScope.launch {
            getBootEventsUseCase()
                .flowOn(Dispatchers.IO)
                .collect { events ->
                    _bootEvents.value = events
                }
        }
    }

    fun saveBootEvent(bootEvent: BootEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            saveBootEventUseCase(bootEvent)
        }
    }
}