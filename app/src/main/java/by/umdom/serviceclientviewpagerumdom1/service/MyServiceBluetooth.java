package by.umdom.serviceclientviewpagerumdom1.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import by.umdom.serviceclientviewpagerumdom1.R;
import by.umdom.serviceclientviewpagerumdom1.event.BluetoothEvent;
import by.umdom.serviceclientviewpagerumdom1.event.ClientEvent;
import by.umdom.serviceclientviewpagerumdom1.fragment.FragmentSettings;

import static by.umdom.serviceclientviewpagerumdom1.service.NotificationHelper.CHANNEL_1_ID;
import static by.umdom.serviceclientviewpagerumdom1.service.NotificationHelper.CHANNEL_2_ID;

public class MyServiceBluetooth extends Service {
    public MyServiceBluetooth() {
    }

    Thread thread;
    //Notification2
    private NotificationManagerCompat notificationManager;

    String[] sbprintArrayStr; //получение данных на планшет по bluetooth

    //Timer
    Timer timer = new Timer();
    //TimerEnd

    //Client
    //static String ipAdress = "375333752202.dyndns.mts.by";
    //static int ipPort = 9002;
    static String ipAdress;
    static int ipPort;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        notificationManager = NotificationManagerCompat.from(this);
        timer.schedule(new UpdateTimeTask(), 0, 5000);

    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        ipAdress = FragmentSettings.dataIpAdress();
        ipPort = FragmentSettings.dataPort();

        //---End Bluetooth---
        return Service.START_STICKY;
    }


    final String LOG_TAG = "myLogs";
    private void sendMessage(final String msg) {
        //final Handler handler = new Handler();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket s = new Socket(ipAdress, ipPort);
                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);
                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    final String  sbprint  = input.readLine();
                    //Log.d(LOG_TAG, "***MyServiceBluetooth: " + sbprint + "***");

                    sbprintArrayStr = sbprint.split(",");
                    //Log.d(LOG_TAG, "***MyServiceBluetooth: " + sbprintArrayStr[1]  + "***");


                    if (sbprint.length() <= 1){
                        EventBus.getDefault().postSticky(new BluetoothEvent( sbprint ));
                    }
                    else if (sbprint.length() >= 10){
                        //оповещение газа в холле
                        if (sbprintArrayStr[14].equals("0")) {
                            notificationGaz1();
                            Log.d(LOG_TAG, "***Сработал датчик газа ");
                        }

                        //оповещение движение в холле[15], OnOffAlarmHoll[16]
                        if (sbprintArrayStr[15].equals("1")) {
                            if (sbprintArrayStr[16].equals("E")) {
                                notificationPir1();
                                Log.d(LOG_TAG, "***Сработал датчик движения ");
                            }
                        }
                        EventBus.getDefault().postSticky(new BluetoothEvent( sbprint ));
                    }

                    output.close();
                    out.close();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //Из фрагмента в сервер
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(ClientEvent event) {
        sendMessage(event.getData());
        Log.e(LOG_TAG, "***Отправляем данные на сервер: " + event.getData() + "***");
    }

    //Timer
    class UpdateTimeTask extends TimerTask {
        public void run() {
            sendMessage("X");
        }
    }

    void notificationGaz1(){
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.iconsinfo)
                .setContentTitle("Сработка газа")
                .setContentText("Наличие газа в холле")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }

    void notificationPir1(){

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.iconsinfo)
                .setContentTitle("Сработка движения 1")
                .setContentText("Наличие движения в холле")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(2, notification);
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        stopSelf();
        thread.interrupt();
        super.onDestroy();
    }
}
