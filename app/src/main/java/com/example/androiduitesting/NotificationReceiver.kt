package com.example.androiduitesting

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver(private val mainActivity: MainActivity) : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        val action = p1!!.action
        Log.i("aaa", "action===$action")
        if ( action == MainActivity.ACTION_DELETE_NOTIFICATION) {
            mainActivity.setNotificationButtonState(true, false, false)
        } else {
            mainActivity.updateNotification()
        }


    }
}