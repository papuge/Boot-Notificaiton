package com.example.bootnotification.domain.repository

import com.example.bootnotification.domain.model.BootEvent

interface BootEventRepository {
    suspend fun insertBootEvent(bootEvent: BootEvent)
    suspend fun getBootEvents(): List<BootEvent>
}