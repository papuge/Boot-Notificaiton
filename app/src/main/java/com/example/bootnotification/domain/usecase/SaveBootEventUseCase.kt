package com.example.bootnotification.domain.usecase

import com.example.bootnotification.domain.model.BootEvent
import com.example.bootnotification.domain.repository.BootEventRepository
import javax.inject.Inject

class SaveBootEventUseCase @Inject constructor(private val repository: BootEventRepository) :
    suspend (BootEvent) -> Unit {

    override suspend fun invoke(bootEvent: BootEvent) {
        repository.insertBootEvent(bootEvent)
    }
}