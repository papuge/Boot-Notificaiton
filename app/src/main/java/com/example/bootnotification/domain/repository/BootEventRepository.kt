package com.example.bootnotification.domain.repository

import com.example.bootnotification.domain.model.BootEvent
import kotlinx.coroutines.flow.Flow

interface BootEventRepository {
    suspend fun insertBootEvent(bootEvent: BootEvent)
    suspend fun getBootEvents(): Flow<List<BootEvent>>
}