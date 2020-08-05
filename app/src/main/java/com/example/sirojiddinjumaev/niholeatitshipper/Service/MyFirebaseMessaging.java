package com.example.sirojiddinjumaev.niholeatitshipper.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.sirojiddinjumaev.niholeatitshipper.Helper.NotificationHelper;
import com.example.sirojiddinjumaev.niholeatitshipper.Common.Common;
import com.example.sirojiddinjumaev.niholeatitshipper.HomeActivity;
import com.example.sirojiddinjumaev.niholeatitshipper.MainActivity;
import com.example.sirojiddinjumaev.niholeatitshipper.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

public class MyFirebaseMessaging extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                sendNotificationAPI26(remoteMessage);
            else
                sendNotification(remoteMessage);
        }


    }

    private void sendNotificationAPI26(RemoteMessage remoteMessage) {

        Map<String,String> data = remoteMessage.getData();
        String title = data.get("title");
        String message = data.get("message");


        //Here we will fix to click to notification => go to OrderList
        PendingIntent pendingIntent;
        NotificationHelper helper;
        android.app.Notification.Builder builder;


        if (Common.currentShipper != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


            helper = new NotificationHelper(this);
            builder = helper.getEatItChannelNotification(title, message, pendingIntent, defaultSoundUri);


            //Get random Id for notification to show all notifications
            helper.getManager().notify(new Random().nextInt(), builder.build());
        }
        else //Fix Crash if notification send from news system (Common.currentUser == null)
        {
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            helper = new NotificationHelper(this);
            builder = helper.getEatItChannelNotification(title, message, defaultSoundUri);

            helper.getManager().notify(new Random().nextInt(), builder.build());
        }
    }

    private void sendNotification(RemoteMessage remoteMessage) {


        //Copy from client app
        Map<String,String> data = remoteMessage.getData();
        String title = data.get("title");
        String message = data.get("message");


        if (Common.currentShipper!=null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_local_shipping_black_24dp)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);


            NotificationManager noti = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            noti.notify(0, builder.build());
        }
        else {

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_local_shipping_black_24dp)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri);



            NotificationManager noti = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            noti.notify(0, builder.build());
        }

    }
}
