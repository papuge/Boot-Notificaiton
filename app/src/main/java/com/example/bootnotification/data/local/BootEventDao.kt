package com.example.bootnotification.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BootEventDao {
    @Insert
    suspend fun insertBootEvent(bootEvent: BootEventEntity)

    @Query("SELECT * FROM boot_events ORDER BY timestamp ASC")
    suspend fun getBootEvents(): List<BootEventEntity>
}
