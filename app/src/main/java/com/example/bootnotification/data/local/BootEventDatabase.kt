package com.example.bootnotification.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context

@Database(entities = [BootEventEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class BootEventDatabase : RoomDatabase() {
    abstract fun bootEventDao(): BootEventDao

    companion object {
        @Volatile
        private var INSTANCE: BootEventDatabase? = null

        fun getDatabase(context: Context): BootEventDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BootEventDatabase::class.java,
                    "boot_event_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
