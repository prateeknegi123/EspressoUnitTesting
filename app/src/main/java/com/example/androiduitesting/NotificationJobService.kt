package com.example.androiduitesting

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat


class NotificationJobService : JobService() {

    companion object {
        val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }

    private lateinit var notificationManager: NotificationManager

    override fun onStopJob(p0: JobParameters?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        //Create the notification channel

        //Create the notification channel
        createNotificationChannel()

//Set up the notification content intent to launch the app when clicked

//Set up the notification content intent to launch the app when clicked
        val contentPendingIntent = PendingIntent.getActivity(
            this, 0, Intent(
                this,
                MainActivity::class.java
            ), PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder =
            NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Job Service")
                .setContentText("Your Job ran to completion!")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_media_play)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true)
        notificationManager.notify(0, builder.build())
        return false
    }

    private fun createNotificationChannel() {
        // Define notification manager object.

        // Define notification manager object.
        notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.O
        ) {

            // Create the NotificationChannel with all the parameters.
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Job Service notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notifications from Job Service"
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}