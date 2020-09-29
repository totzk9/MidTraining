package com.lig.midtraining.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.lig.midtraining.R
import com.lig.midtraining.view.MainActivity
import java.util.*


class FCMService : FirebaseMessagingService() {
    private lateinit var notification: Notification
    private val notificationId: Int = 1000

    companion object {

        const val CHANNEL_ID = "com.lig.midtraining.notif"
        const val CHANNEL_NAME = "notification"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e("NOTIF RECEIVED", remoteMessage.notification.toString())
        if (remoteMessage != null) {
            handleMessage(remoteMessage)
        }
    }

    override fun onNewToken(token: String) {
//        sendRegistrationToServer(token)
    }


    @SuppressLint("NewApi")
    private fun createChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                this.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.lightColor = Color.parseColor("#424242")
            notificationChannel.description = "Do your task"

            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

            notificationManager.createNotificationChannel(notificationChannel)

        }

    }


    private fun handleMessage(remoteMessage: RemoteMessage) {
        createChannel()
        val notifyIntent = Intent(this, MainActivity::class.java)

        val title = remoteMessage.notification!!.title
        val body = remoteMessage.notification!!.body

        notifyIntent.putExtra("title", title)
        notifyIntent.putExtra("notification", true)

        notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val pendingIntent = PendingIntent.getActivity(
            this.applicationContext, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            notification = Notification.Builder(this, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_image)
                .setAutoCancel(true)
                .setContentTitle(title)
                .build()
        } else {
            notification = Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_image)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setSound(uri)
                .build()
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(notificationId, notification)


    }

}