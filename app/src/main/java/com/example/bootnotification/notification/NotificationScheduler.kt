package com.example.bootnotification.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.bootnotification.domain.model.BootEvent
import com.example.bootnotification.presentation.receiver.NotificationReceiver
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class NotificationScheduler(private val context: Context) {

    companion object {
        const val NOTIFICATION_ID = 1001
        private const val ALARM_REQUEST_CODE = 1002
        private const val CHANNEL_ID = "Boot channel"
    }

    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager

    private var dismissalCount = 0
    private val maxDismissals = 5

    fun scheduleNotification() {
        val triggerTime = calculateNextAlarmTime(false)
        val notificationContent = createNotificationContent()

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Boot Notification")
            .setContentText(notificationContent)
            .build()

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            ALARM_REQUEST_CODE,
            Intent(context, NotificationReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager?.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerTime.timeInMillis,
            pendingIntent
        )
    }

    // TODO: Change seconds
    private fun calculateNextAlarmTime(withDismissalInterval: Boolean): Calendar {
        val now = Calendar.getInstance()
        now.add(Calendar.SECOND, if (withDismissalInterval)20 else 15)
        return now
    }

    private fun createNotificationContent(): String {
        val bootEvent = getLatestBootEvent()
        return when {
            bootEvent == null -> "No boots detected"
            else -> "The boot was detected = ${formatDate(bootEvent.timestamp)}"
        }
    }

    private fun getLatestBootEvent(): BootEvent? {
        // Implement logic to fetch the latest boot event from your repository
        return null // Replace with actual implementation
    }

    private fun formatDate(timestamp: Date): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return dateFormat.format(timestamp)
    }

    private fun calculateTimeDelta(): String {
        return "1 hour ago" // Replace with actual calculation
    }
}
