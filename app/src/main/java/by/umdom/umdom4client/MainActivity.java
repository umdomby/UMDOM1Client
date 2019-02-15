package by.umdom.umdom4client;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Girish Bhalerao on 5/4/2017.
 */
public class MainActivity extends AppCompatActivity {

    //Client
    //String ipAdress = "375297053199.dyndns.mts.by"; //UMDOM4
    String ipAdress = "375333752202.dyndns.mts.by";
    String[] sbprintArrayStr;
    int ipPort = 9002;
    //MyClientTask myClientTask;
    // ClientEnd

    private final static String TAG = MainActivity.class.getSimpleName();
    final String LOG_TAG = "myLogs";
    ImageButton ImageButton1, ImageButton2,  ImageButton3, ImageButton4, ImageButton5, ImageButton6, ImageButton7, ImageButton8, ImageButtonObnov, ImageButtonOnOff;
    TextView textTemp;
    boolean flag1 = true, flag2 = true, flag3 = true, flag4 = true, flag5 = true, flag6 = true, flag7 = true, flag8 = true, flagonoff = true, flagobnov = true;
    boolean requestone = true;

    public TextView textInfo; //получение данных о телефоне
    //String dataTempB; //получение температуры String
    //String dataVlag; //получение влажности
    String firstTwo; //получение данных на планшет по bluetooth
    String firstTwo2; //получение данных от сервера

    //Timer
    Timer timer = new Timer();
    //TimerEnd


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Client
        //Timer

        timer.schedule(new UpdateTimeTask(), 0, 5000);

        //TimerEnd
        //ClientEnd

        ImageButton1 = (ImageButton) findViewById(R.id.ImageButton1);
        ImageButton2 = (ImageButton) findViewById(R.id.ImageButton2);
        ImageButton3 = (ImageButton) findViewById(R.id.ImageButton3);
        ImageButton4 = (ImageButton) findViewById(R.id.ImageButton4);
        ImageButton5 = (ImageButton) findViewById(R.id.ImageButton5);
        ImageButton6 = (ImageButton) findViewById(R.id.ImageButton6);
        ImageButton7 = (ImageButton) findViewById(R.id.ImageButton7);
        ImageButton8 = (ImageButton) findViewById(R.id.ImageButton8);
        ImageButtonObnov = (ImageButton) findViewById(R.id.ImageButtonObnov);
        ImageButtonOnOff = (ImageButton) findViewById(R.id.ImageButtonOnOff);
        textTemp = (TextView)findViewById(R.id.textTemp);


        ImageButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag1 == true) {
                    sendMessage("B");
                    flag1 = false;
                }
                else {

                    sendMessage("b");
                    flag1 = true; }
            }
        });

        ImageButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag2 == true) {
                    sendMessage("C");
                    flag2 = false;
                }
                else {
                    sendMessage("c");
                    flag2 = true; }
            }
        });

        ImageButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag3 == true) {
                    sendMessage("D");
                    flag3 = false;
                }
                else {
                    sendMessage("d");
                    flag3 = true; }
            }
        });

        ImageButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag4 == true) {
                    sendMessage("E");
                    flag4 = false;
                }
                else {
                    sendMessage("e");
                    flag4 = true; }
            }
        });

        ImageButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag5 == true) {
                    sendMessage("F");
                    flag5 = false;
                }
                else {
                    sendMessage("f");
                    flag5 = true; }
            }
        });

        ImageButton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag6 == true) {
                    sendMessage("G");
                    flag6 = false;
                }
                else {
                    sendMessage("g");
                    flag6 = true; }
            }
        });

        ImageButton7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag7 == true) {
                    sendMessage("H");
                    flag7 = false;
                }
                else {
                    sendMessage("h");
                    flag7 = true; }
            }
        });

        ImageButton8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag8 == true) {
                    sendMessage("I");
                    flag8 = false;
                }
                else {
                    sendMessage("i");
                    flag8 = true; }
            }
        });


        ImageButtonOnOff.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flagonoff == true) {
                    sendMessage("A");
                    flagonoff = false;
                }
                else {
                    sendMessage("a");
                    flagonoff = true; }
            }
        });

        ImageButtonObnov.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage("X");
            }
        });


    } //END onCreate


    private void sendMessage(final String msg) {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //Replace below IP with the IP of that device in which server socket open.
                    //If you change port then change the port number in the server side code also.
                    Socket s = new Socket(ipAdress, ipPort);

                    OutputStream out = s.getOutputStream();
                    PrintWriter output = new PrintWriter(out);

                    output.println(msg);
                    output.flush();
                    BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));

                    final String st = input.readLine();

                    //sbprintArrayStr = st.split(",");

                    //byte[] bytes = {...}
                    //String str = new String(bytes, "UTF-8"); // for UTF-8 encoding
                    Log.d(LOG_TAG, "***приём: " + st + "***"  );

                    switch (st) {

                        case "b":
                            flag1 = true;
                            ImageButton1.setImageResource(R.drawable.one);
                            break;
                        case "B":
                            flag1 = false;
                            ImageButton1.setImageResource(R.drawable.oneg);
                            break;
                        case "c":
                            flag2 = true;
                            ImageButton2.setImageResource(R.drawable.two);
                            break;
                        case "C":
                            flag2 = false;
                            ImageButton2.setImageResource(R.drawable.twog);
                            break;

                            default:
                                break;
                    }


                    if(st.length() > 10 ) {
                        sbprintArrayStr = st.split(",");
                        //Log.d(LOG_TAG, "***приём: " + Arrays.toString(sbprintArrayStr) + "***"  );

                        if (sbprintArrayStr[1].equals("b")) {
                            flag1 = true;
                            ImageButton1.setImageResource(R.drawable.one);
                        }
                        if (sbprintArrayStr[1].equals("B")) {
                            flag1 = false;
                            ImageButton1.setImageResource(R.drawable.oneg);
                        }
                        if (sbprintArrayStr[2].equals("c")) {
                            flag2 = true;
                            ImageButton2.setImageResource(R.drawable.two);
                        }
                        if (sbprintArrayStr[2].equals("C")) {
                            flag2 = false;
                            ImageButton2.setImageResource(R.drawable.twog);
                        }
                        if (sbprintArrayStr[3].equals("d")) {
                            flag3 = true;
                            ImageButton3.setImageResource(R.drawable.three);
                        }
                        if (sbprintArrayStr[3].equals("D")) {
                            flag3 = false;
                            ImageButton3.setImageResource(R.drawable.threeg);
                        }
                        if (sbprintArrayStr[4].equals("e")) {
                            flag4 = true;
                            ImageButton4.setImageResource(R.drawable.four);
                        }
                        if (sbprintArrayStr[4].equals("E")) {
                            flag4 = false;
                            ImageButton4.setImageResource(R.drawable.fourg);
                        }
                        if (sbprintArrayStr[5].equals("f")) {
                            flag5 = true;
                            ImageButton5.setImageResource(R.drawable.five);
                        }
                        if (sbprintArrayStr[5].equals("F")) {
                            flag5 = false;
                            ImageButton5.setImageResource(R.drawable.fiveg);
                        }
                        if (sbprintArrayStr[6].equals("g")) {
                            flag6 = true;
                            ImageButton6.setImageResource(R.drawable.six);
                        }
                        if (sbprintArrayStr[6].equals("G")) {
                            flag6 = false;
                            ImageButton6.setImageResource(R.drawable.sixg);
                        }
                        if (sbprintArrayStr[7].equals("h")) {
                            flag7 = true;
                            ImageButton7.setImageResource(R.drawable.seven);
                        }
                        if (sbprintArrayStr[7].equals("H")) {
                            flag7 = false;
                            ImageButton7.setImageResource(R.drawable.seveng);
                        }
                        if (sbprintArrayStr[8].equals("i")) {
                            flag8 = true;
                            ImageButton8.setImageResource(R.drawable.eight);
                        }
                        if (sbprintArrayStr[8].equals("I")) {
                            flag8 = false;
                            ImageButton8.setImageResource(R.drawable.eightg);
                        }
                        textTemp.setText(sbprintArrayStr[9] + " " + sbprintArrayStr[10] + " " + sbprintArrayStr[11] + " " + sbprintArrayStr[12]
                                + " " + sbprintArrayStr[13] + " " + sbprintArrayStr[14] + " " + sbprintArrayStr[15]);
                    }



//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//
////                           String s = mTextViewReplyFromServer.getText().toString();
////                            if (st.trim().length() != 0)
////                                mTextViewReplyFromServer.setText(s + "\nFrom Server : " + st);
//
//                            firstTwo2 = st.substring(0,1); //получение обозначения
//                            //Log.d(LOG_TAG, "***From st: " + st);
//
//                            switch (firstTwo2) {
//
//                                case "b":
//                                    flag1 = true;
//                                    ImageButton1.setImageResource(R.drawable.one);
//                                    break;
//                                case "B":
//                                    flag1 = false;
//                                    ImageButton1.setImageResource(R.drawable.oneg);
//                                    break;
//                                case "c":
//                                    flag2 = true;
//                                    ImageButton2.setImageResource(R.drawable.two);
//                                    break;
//                                case "C":
//                                    flag2 = false;
//                                    ImageButton2.setImageResource(R.drawable.twog);
//                                    break;
//                                case "d":
//                                    flag3 = true;
//                                    ImageButton3.setImageResource(R.drawable.three);
//                                    break;
//                                case "D":
//                                    flag3 = false;
//                                    ImageButton3.setImageResource(R.drawable.threeg);
//                                    break;
//                                case "e":
//                                    flag4 = true;
//                                    ImageButton4.setImageResource(R.drawable.four);
//                                    break;
//                                case "E":
//                                    flag4 = false;
//                                    ImageButton4.setImageResource(R.drawable.fourg);
//                                    break;
//                                case "f":
//                                    flag5 = true;
//                                    ImageButton5.setImageResource(R.drawable.five);
//                                    break;
//                                case "F":
//                                    flag5 = false;
//                                    ImageButton5.setImageResource(R.drawable.fiveg);
//                                    break;
//                                case "g":
//                                    flag6 = true;
//                                    ImageButton6.setImageResource(R.drawable.six);
//                                    break;
//                                case "G":
//                                    flag6 = false;
//                                    ImageButton6.setImageResource(R.drawable.sixg);
//                                    break;
//                                case "h":
//                                    flag7 = true;
//                                    ImageButton7.setImageResource(R.drawable.seven);
//                                    break;
//                                case "H":
//                                    flag7 = false;
//                                    ImageButton7.setImageResource(R.drawable.seveng);
//                                    break;
//                                case "i":
//                                    flag8 = true;
//                                    ImageButton8.setImageResource(R.drawable.eight);
//                                    break;
//                                case "I":
//                                    flag8 = false;
//                                    ImageButton8.setImageResource(R.drawable.eightg);
//                                    break;
//                                case "a":
//                                    ImageButton1.setImageResource(R.drawable.one);
//                                    ImageButton2.setImageResource(R.drawable.two);
//                                    ImageButton3.setImageResource(R.drawable.three);
//                                    ImageButton4.setImageResource(R.drawable.four);
//                                    ImageButton5.setImageResource(R.drawable.five);
//                                    ImageButton6.setImageResource(R.drawable.six);
//                                    ImageButton7.setImageResource(R.drawable.seven);
//                                    ImageButton8.setImageResource(R.drawable.eight);
//                                    ImageButtonOnOff.setImageResource(R.drawable.off);
//                                    flagonoff = true; flag2 = true; flag3 = true; flag4 = true; flag5 = true; flag6 = true; flag7 = true; flag8 = true;
//                                    break;
//                                case "A":
//                                    ImageButton1.setImageResource(R.drawable.oneg);
//                                    ImageButton2.setImageResource(R.drawable.twog);
//                                    ImageButton3.setImageResource(R.drawable.threeg);
//                                    ImageButton4.setImageResource(R.drawable.fourg);
//                                    ImageButton5.setImageResource(R.drawable.fiveg);
//                                    ImageButton6.setImageResource(R.drawable.sixg);
//                                    ImageButton7.setImageResource(R.drawable.seveng);
//                                    ImageButton8.setImageResource(R.drawable.eightg);
//                                    ImageButtonOnOff.setImageResource(R.drawable.on);
//                                    flagonoff = false; flag2 = false; flag3 = false; flag4 = false; flag5 = false; flag6 = false; flag7 = false; flag8 = false;
//                                    break;
//                                case "X":
//                                    textTemp.setText(st);
//                                    break;
//                                default:
//                                    break;
//                            }
//
//                        }
//                    });

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

    //Timer
    class UpdateTimeTask extends TimerTask {
        public void run() {
            sendMessage("X");
        }
    }

//        private Runnable Timer_Tick = new Runnable() {
//        public void run() {
//            sendMessage("X");
//        }
//    };
    //TimerEnd
}