package com.example.asetdsi.services;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.asetdsi.HistoryActivity;
import com.example.asetdsi.LoginActivity;
import com.example.asetdsi.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class PushNotificationService extends FirebaseMessagingService{
    private static  final String CHANNEL_ID = "com.example.asetdsi.CH01";

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("fcm-token", s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title  = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();
        showNotification(title,message);
    }

    private void showNotification(String title, String message) {
        //ambil objek notification magaer
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        //BuatChannel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "Channel AsetDSI",
                            NotificationManager.IMPORTANCE_DEFAULT
                    );
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //Buat Pending Intent
        Intent historyIntent = new Intent(getApplicationContext(), HistoryActivity.class);

        PendingIntent pendingHistoryIntenet = PendingIntent.getActivity(
                getApplicationContext(),
                12345,
                historyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        //Buat Notifikasi
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.logo_notif)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setContentIntent(pendingHistoryIntenet)
                        .addAction(R.drawable.ic_baseline_near_me_24, "Lihat", pendingHistoryIntenet);
        ;

        Notification notification = builder.build();

        //Tamoilkan notifikasi
        Random random = new Random(1000);

        notificationManager.notify(random.nextInt(), notification);


    }
}