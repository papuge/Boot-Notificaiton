package com.example.bootnotification.presentation.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.bootnotification.domain.model.BootEvent
import com.example.bootnotification.domain.usecase.GetBootEventsUseCase
import com.example.bootnotification.domain.usecase.SaveBootEventUseCase
import com.example.bootnotification.presentation.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var saveBootEventUseCase: SaveBootEventUseCase

    @Inject
    lateinit var getBootEventUseCase: GetBootEventsUseCase

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            CoroutineScope(Dispatchers.Main).launch {
                saveBootEventUseCase(BootEvent(timestamp = Date()))
                getBootEventUseCase().collectLatest {
                    setAlarm(it.size, context)
                }
                Toast.makeText(context, "Boot completed detected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setAlarm(rebootsCount: Int, context: Context?){
        // TODO: get trigger time
        val triggerTime = 90000L // 15 minutes
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 12345, intent, PendingIntent.FLAG_IMMUTABLE)
        val mainActivityIntent = Intent(context, MainActivity::class.java)
        val basicPendingIntent = PendingIntent.getActivity(context, 12345, mainActivityIntent, PendingIntent.FLAG_IMMUTABLE)
        val clockInfo = AlarmManager.AlarmClockInfo(triggerTime, basicPendingIntent)
        alarmManager.setAlarmClock(clockInfo, pendingIntent)
    }
}