package com.example.e_commerce.ui.main.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.e_commerce.R
import com.example.e_commerce.ui.main.MainActivity







class MyAlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {



        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)


        val intentConfirm = Intent(context!!, NotificationActionReceiver::class.java)
        intentConfirm.setAction("CONFIRM")
        intentConfirm.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)


        val intentCancel = Intent(context!!, NotificationActionReceiver::class.java)
        intentCancel.setAction("CANCEL")
        intentCancel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntentCancel =
            PendingIntent.getBroadcast(context!!, 1, intentCancel, PendingIntent.FLAG_CANCEL_CURRENT)

        val pendingIntentConfirm =
            PendingIntent.getBroadcast(context!!, 0, intentConfirm, PendingIntent.FLAG_CANCEL_CURRENT)



        val pendingIntent = PendingIntent.getActivity(
            context, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = context!!.getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(context!!, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(context!!.getString(R.string.app_name))
            .setContentText("Wash Hand")
            .setAutoCancel(true)
            .addAction(R.drawable.ic_notification, "YES", pendingIntentConfirm)
            .addAction(R.drawable.ic_notification, "NO", pendingIntentCancel)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())

    }
}
