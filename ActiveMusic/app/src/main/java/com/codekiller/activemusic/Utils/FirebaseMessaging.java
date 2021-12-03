package com.codekiller.activemusic.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.codekiller.activemusic.MainActivity;
import com.codekiller.activemusic.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessaging extends FirebaseMessagingService {
    public static final String TAG = "FIREBASE MESSAGING";
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "onMessageReceived: from - "+remoteMessage.getFrom());
        Log.d(TAG, "onMessageReceived: body - "+remoteMessage.getNotification().getBody());
        Log.d(TAG, "onMessageReceived: channel id - "+remoteMessage.getNotification().getChannelId());
        sendNotification(remoteMessage.getNotification().getBody(),"notify");
    }

    private void sendNotification(String body, String channelId) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.music)
                .setSound(ringtoneUri)
                .setContentTitle("Active Music")
                .setContentText(body)
                .setAutoCancel(true)
                .setChannelId(channelId);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelId,"notification",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        assert notification != null;
        notificationManager.notify(0,notification.build());
    }
}
