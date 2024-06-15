package com.example.bootnotification.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bootnotification.domain.usecase.GetBootEventsUseCase
import com.example.bootnotification.domain.usecase.SaveBootEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBootEventsUseCase: GetBootEventsUseCase,
    private val saveBootEventUseCase: SaveBootEventUseCase
) : ViewModel() {

}