package com.example.bootnotification.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.bootnotification.domain.model.BootEvent
import com.example.bootnotification.domain.usecase.SaveBootEventUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var saveBootEventUseCase: SaveBootEventUseCase

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            CoroutineScope(Dispatchers.IO).launch {
                saveBootEventUseCase(BootEvent(timestamp = Date()))
                Toast.makeText(context, "Boot completed detected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}