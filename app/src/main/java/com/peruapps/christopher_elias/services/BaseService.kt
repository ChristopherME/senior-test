package com.peruapps.christopher_elias.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.peruapps.christopher_elias.R
import com.peruapps.christopher_elias.ui.activities.main.Main
import com.peruapps.christopher_elias.utils.VersionUtils
import dagger.android.AndroidInjection


/**
 * Created by Christopher Elias on 30-may-2019
 * christopher.mike.96@gmail.com

 * Lima, Perú
 * Copyright (c) 2019 All rights reserved.
 */

abstract class BaseService : Service(){

    companion object {
        const val CORE_POOL_SIZE = 1
        const val TIME_PERIOD = 5L //every 30 minutes.
        const val NOTIFICATION_ID = 1115
        const val NOTIFICATION_CHANNEL_ID = "11"
        const val NOTIFICATION_CHANNEL_NAME = "senior_test_places"
        const val NOTIFICATION_INTENT_EXTRA = "background_service"
    }

    override fun onCreate() {
        super.onCreate()
        AndroidInjection.inject(this)
    }

    // This has to be done in order to keep the service running
    // while the app has been killed.
    protected fun createNotification() {

        val notificationIntent = Intent(applicationContext, Main::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(NOTIFICATION_INTENT_EXTRA, true)
        }

        val contentPendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT)
        val notificationIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_access_alarm)

        val notification = NotificationCompat.Builder(this, createNotificationChannel())
            .setSmallIcon(R.drawable.ic_access_alarm)
            .setLargeIcon(Bitmap.createScaledBitmap(notificationIcon, 128, 128, false))
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.notification_content_text))
            .setStyle(NotificationCompat.BigTextStyle().bigText(getString(R.string.notification_content_text)))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(contentPendingIntent)
            .setColorized(true)
            .setOngoing(true)
            .build()

        notification.flags = notification.flags or Notification.FLAG_NO_CLEAR

        startForeground(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel(): String {
        return if (!VersionUtils.isOreo()){
            // If is not greater than oreo, it will just return a string.
            // If not, apart from returning a string first will create a notification channel.
            NOTIFICATION_CHANNEL_ID
        } else {

            val chan = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_NONE).apply {
                lightColor = Color.BLUE
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            }

            val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            service.createNotificationChannel(chan)

            NOTIFICATION_CHANNEL_ID
        }
    }

    protected fun logError(message: String?) {
        Log.e(this.javaClass.simpleName,message?:"null")
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}