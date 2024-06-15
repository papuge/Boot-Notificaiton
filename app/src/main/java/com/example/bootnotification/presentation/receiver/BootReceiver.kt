package com.example.bootnotification.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.bootnotification.data.repository.BootEventRepositoryImpl
import com.example.bootnotification.domain.usecase.SaveBootEventUseCase
import com.example.bootnotification.notification.NotificationScheduler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var saveBootEventUseCase: SaveBootEventUseCase

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // TODO(): Save event and show notification

            Toast.makeText(context, "Boot completed detected", Toast.LENGTH_SHORT).show()
        }
    }
}