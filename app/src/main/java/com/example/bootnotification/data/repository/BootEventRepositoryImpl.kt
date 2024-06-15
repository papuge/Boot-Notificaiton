package com.example.bootnotification.data.repository

import com.example.bootnotification.data.local.BootEventDao
import com.example.bootnotification.data.local.BootEventEntity
import com.example.bootnotification.domain.model.BootEvent
import com.example.bootnotification.domain.repository.BootEventRepository
import javax.inject.Inject

class BootEventRepositoryImpl @Inject constructor(private val bootEventDao: BootEventDao) :
    BootEventRepository {

    override suspend fun insertBootEvent(bootEvent: BootEvent) {
        bootEventDao.insertBootEvent(BootEventEntity(timestamp = bootEvent.timestamp))
    }

    override suspend fun getBootEvents(): List<BootEvent> {
        return bootEventDao.getBootEvents().map { BootEvent(it.id, it.timestamp) }
    }
}

