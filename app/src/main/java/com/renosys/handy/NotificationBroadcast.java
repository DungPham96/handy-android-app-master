package com.renosys.handy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by cpu0131 on 03/11/2017.
 */

public class NotificationBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive( final Context context, Intent intent) {

        Intent notificationIntent = new Intent(context, MainActivity.class );

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack( MainActivity.class );
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder
                .getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT );

        // content notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder( context );
        Notification notification = builder
                .setContentTitle( context.getString( R.string.title ) )
                .setContentText( context.getString( R.string.content ) )
                .setTicker( context.getString( R.string.title ) )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();

        //Vibrate for 1000 milliseconds (1s)
        Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

     }
}

