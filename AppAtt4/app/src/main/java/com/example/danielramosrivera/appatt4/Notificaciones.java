package com.example.flaco.noti20;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by flaco on 07/07/2017.
 */
public class Notificaciones extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager nt = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Asistencia")
                .setContentText("Subir la lista de asistencia de los grupos!")
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());

        nt.notify(2,builder.build());
        
    }
}
