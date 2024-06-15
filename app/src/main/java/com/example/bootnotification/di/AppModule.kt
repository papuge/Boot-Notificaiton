package com.example.bootnotification.di

import android.content.Context
import androidx.room.Room
import com.example.bootnotification.data.local.BootEventDao
import com.example.bootnotification.data.local.BootEventDatabase
import com.example.bootnotification.data.repository.BootEventRepositoryImpl
import com.example.bootnotification.domain.repository.BootEventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BootEventDatabase {
        return Room.databaseBuilder(
            context,
            BootEventDatabase::class.java,
            "boot_event_database"
        ).build()
    }

    @Provides
    fun provideBootEventDao(database: BootEventDatabase): BootEventDao {
        return database.bootEventDao()
    }

    @Provides
    @Singleton
    fun provideBootEventRepository(impl: BootEventRepositoryImpl): BootEventRepository {
        return impl
    }
}