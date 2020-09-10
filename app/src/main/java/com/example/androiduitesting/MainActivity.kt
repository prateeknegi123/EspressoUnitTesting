package com.example.androiduitesting

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object {
        private val PRIMARY_CHANNEL_ID: String = "primary_notification_channel"
        private val ACTION_UPDATE_NOTIFICATION: String =
            "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION"
        val ACTION_DELETE_NOTIFICATION: String =
            "com.example.android.notifyme.ACTION_DELETE_NOTIFICATION"
        private val NOTIFICATION_ID: Int = 0
    }

    private lateinit var mNotifyManager: NotificationManager
    private lateinit var notificationReceiver: NotificationReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationReceiver = NotificationReceiver(this@MainActivity)
        createNotificationChannel()
        setNotificationButtonState(true, false, false)
        notify.setOnClickListener {
            sendNotification()
        }

        update.setOnClickListener {
            updateNotification()
        }

        cancel.setOnClickListener {
            cancelNotification()
        }

        registerReceiver(notificationReceiver, IntentFilter(ACTION_UPDATE_NOTIFICATION))
    }

    private fun createNotificationChannel() {
        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID, "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notification from Mascot"
            mNotifyManager.createNotificationChannel(notificationChannel)
        }
    }


    private fun sendNotification() {
        val updateIntent = Intent(ACTION_UPDATE_NOTIFICATION)
        val updatePendingIntent = PendingIntent.getBroadcast(
            this,
            NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT
        )

        val deleteIntent = Intent(ACTION_DELETE_NOTIFICATION)
        val deletePendingIntent = PendingIntent.getBroadcast(
            this,
            NOTIFICATION_ID, deleteIntent, PendingIntent.FLAG_ONE_SHOT
        )

        val notifyBuilder = getNotificationBuilder()

        notifyBuilder
            .addAction(R.drawable.ic_action_name, "Update", updatePendingIntent)
            .setDeleteIntent(deletePendingIntent)


        mNotifyManager.notify(
            NOTIFICATION_ID, notifyBuilder.build()
        )
        setNotificationButtonState(false, true, true)
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("You've been notified!")
            .setContentText("This is your notification text.")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
    }

    private fun pendingIntent(): PendingIntent {
        val notificationIntent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(
            this,
            NOTIFICATION_ID,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    fun updateNotification() {
        val androidImage = BitmapFactory
            .decodeResource(resources, R.drawable.mascot_1)

        val notifyBuilder = getNotificationBuilder()
        notifyBuilder.setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("Notification Updated!")
        )
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build())
        setNotificationButtonState(false, false, true)
    }

    fun setNotificationButtonState(
        isNotifyEnabled: Boolean?,
        isUpdateEnabled: Boolean?,
        isCancelEnabled: Boolean?
    ) {
        notify.isEnabled = isNotifyEnabled!!
        update.isEnabled = isUpdateEnabled!!
        cancel.isEnabled = isCancelEnabled!!
    }

    private fun cancelNotification() {
        mNotifyManager.cancel(NOTIFICATION_ID)
        setNotificationButtonState(true, false, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu!!.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu!!.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        }

        return true
    }

    override fun onDestroy() {
        unregisterReceiver(notificationReceiver)
        super.onDestroy()
    }
}