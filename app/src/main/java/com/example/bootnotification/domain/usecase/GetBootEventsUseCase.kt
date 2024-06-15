package com.example.bootnotification.domain.usecase

import com.example.bootnotification.data.repository.BootEventRepositoryImpl
import com.example.bootnotification.domain.model.BootEvent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBootEventsUseCase @Inject constructor(private val repository: BootEventRepositoryImpl) :
    suspend () -> Flow<List<BootEvent>> {

    override suspend fun invoke(): Flow<List<BootEvent>> {
        return repository.getBootEvents()
    }
}