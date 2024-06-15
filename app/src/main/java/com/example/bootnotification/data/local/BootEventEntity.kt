package com.example.bootnotification.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "boot_events")
data class BootEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timestamp: Date
)
