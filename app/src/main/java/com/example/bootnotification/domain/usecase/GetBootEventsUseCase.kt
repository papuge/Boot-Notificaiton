package com.example.bootnotification.domain.usecase

import com.example.bootnotification.data.repository.BootEventRepositoryImpl
import com.example.bootnotification.domain.model.BootEvent
import javax.inject.Inject

class GetBootEventsUseCase @Inject constructor(private val repository: BootEventRepositoryImpl) :
    suspend () -> List<BootEvent> {

    override suspend fun invoke(): List<BootEvent> {
        return repository.getBootEvents()
    }
}