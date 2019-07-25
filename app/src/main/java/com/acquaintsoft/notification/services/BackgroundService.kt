package com.acquaintsoft.notification.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.acquaintsoft.notification.R
import com.acquaintsoft.notification.utils.Common
import com.acquaintsoft.notification.utils.Constants
import com.acquaintsoft.notification.utils.PrefUtils


class BackgroundService : Service() {

    companion object {
        var ID_SMALL_NOTIFICATION = 1
    }

    override fun onBind(intent: Intent): IBinder? {
        return Binder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        PrefUtils.saveBoolean(applicationContext, Constants.PREF_ON_STARTED, true)
        //5 min countdown timer
        val c = object : CountDownTimer((1000 * 60 * 5), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //remaining time calculation mm:ss format
                var minutes = (millisUntilFinished / (1000 * 60)).toInt()
                var seconds = (millisUntilFinished / (1000)).toInt()
                seconds = seconds % 60
                showNotification(Common.getDigits(minutes), Common.getDigits(seconds))
            }

            override fun onFinish() {
                //timer time is finished then remove notification
                stopForeground(true)
            }
        }
        c.start()
        return super.onStartCommand(intent, flags, startId)
    }

    //show notification
    private fun showNotification(minutes: String, seconds: String) {
        //If android version more then oreo then create notification channel
        val mBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(applicationContext, createNotificationChannel())
        } else {
            NotificationCompat.Builder(applicationContext)
        }
        val notification: Notification
        notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentTitle("You are on the break")
            .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_SOUND or Notification.DEFAULT_LIGHTS)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(true)
            .setContentText("Time left: $minutes:$seconds")
            .setChannelId("" + ID_SMALL_NOTIFICATION)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
        startForeground(ID_SMALL_NOTIFICATION, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): String {
        val channelId = "" + ID_SMALL_NOTIFICATION
        var channelName = "Background Service"
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_LOW
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}
