package by.umdom.serviceclientviewpagerumdom1.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ClientService extends Service {
    public ClientService() {
    }

    private NotificationHelper notificationHelper;

    public static void start(Context context) {
        Intent starter = new Intent(context, ClientService.class);
        context.startService(starter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        notificationHelper = new NotificationHelper(this);
        startForeground(NotificationHelper.NOTIFICATION_ID,
                notificationHelper.createNotification("Сервер работает"));

        return START_NOT_STICKY;
    }
    @Override
    public void onDestroy() {
        stopSelf();
        super.onDestroy();
    }
}


