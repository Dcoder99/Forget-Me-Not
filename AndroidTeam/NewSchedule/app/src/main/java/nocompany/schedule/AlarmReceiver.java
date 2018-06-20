package nocompany.schedule;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mainIntent = new Intent(context, Schedulemain.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        String message = intent.getStringExtra("task");
        int notificationId = intent.getIntExtra("notificationId", 0);

        NotificationManager myNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info).setContentTitle("Reminder!!!").setContentText(message)
                .setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(contentIntent).setSound(soundUri);

        myNotificationManager.notify(notificationId, builder.build());
        Vibrator v = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);



    }
}
