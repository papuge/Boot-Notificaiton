package com.example.bootnotification

import android.app.Application
import com.example.bootnotification.notification.NotificationScheduler
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BootNotificationApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Schedule notification when application starts
        val notificationScheduler = NotificationScheduler(applicationContext)
        notificationScheduler.scheduleNotification()
    }
}