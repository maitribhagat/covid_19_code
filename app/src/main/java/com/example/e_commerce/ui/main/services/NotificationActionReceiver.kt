package com.example.e_commerce.ui.main.services

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.R.string.cancel
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.e_commerce.R
import com.example.e_commerce.ui.main.MainActivity


class NotificationActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.getAction().equals("CONFIRM")) {

            Toast.makeText(context, "Very Good.", Toast.LENGTH_SHORT).show()

            val notificationManager =
                context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancelAll()





            val intent = Intent(context!!, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingIntent = PendingIntent.getActivity(
                context!!, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT
            )

            val channelId = context!!.getString(R.string.default_notification_channel_id)
            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val notificationBuilder = NotificationCompat.Builder(context!!, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(context!!.getString(R.string.app_name))
                .setContentText("Very Good Job.")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

            val notificationManager1 =
                context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Since android Oreo notification channel is needed.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager1.createNotificationChannel(channel)
            }

            notificationManager1.notify(0 /* ID of notification */, notificationBuilder.build())


        } else if (intent.getAction().equals("CANCEL")) {

            val notificationManager =
                context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancelAll()
        }


    }
}
