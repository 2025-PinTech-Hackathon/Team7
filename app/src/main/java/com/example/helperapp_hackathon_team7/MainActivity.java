package com.example.helperapp_hackathon_team7;

import android.app.ActivityManager;
import android.content.*;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.*;
import android.view.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.SyncFailedException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] menu = {"Bill", "Oversea", "Membership", "Gift", "GoodDeal", "Near"};

    int nextMenu = 0;
    private final BroadcastReceiver requestInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.HelperApp_Prototype.ACTION_REQUEST_INFO".equals(intent.getAction())) {
                String request = intent.getStringExtra("result");
                Log.d("MainActivity", "버튼 정보 요청 수신");

                LinearLayout send_Button = findViewById(R.id.send_Button);
                ImageView payment_Button = findViewById(R.id.payment_Button);
                ImageButton menuButton = findViewById(R.id.imageButton);
                Log.d("MainActivity", "수신된 정보");

                if(request.equals("Send")){
                    Log.d("MainActivity", "Send 버튼 정보 요청 수신");
                    send_Button.post(() -> {
                        try {
                            JSONObject obj = new JSONObject();
                            int[] location = new int[2];
                            send_Button.getLocationOnScreen(location);

                            obj.put("id", "Send");
                            obj.put("x", location[0]);
                            obj.put("y", location[1]);
                            obj.put("width", send_Button.getWidth());
                            obj.put("height", send_Button.getHeight());

                            JSONObject payload = new JSONObject();
                            payload.put("screen", "MainActivity");
                            JSONArray buttons = new JSONArray();
                            buttons.put(obj);
                            payload.put("buttons", buttons);

                            Intent broadcastIntent = new Intent();
                            broadcastIntent.setAction("com.HelperApp_Prototype.ACTION_BUTTON_INFO");
                            broadcastIntent.setComponent(new ComponentName(
                                    "com.example.team7_realhelper",
                                    "com.example.team7_realhelper.MyReceiver"));
                            broadcastIntent.putExtra("payload", payload.toString());
                            Log.d("MainActivity", "전송된 정보: " + payload.toString());

                            sendBroadcast(broadcastIntent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
                }
                else if(request.equals("QR")){
                    Log.d("MainActivity", "QR 버튼 정보 요청 수신");
                    payment_Button.post(() -> {
                        try {
                            JSONObject obj = new JSONObject();
                            int[] location = new int[2];
                            payment_Button.getLocationOnScreen(location);

                            obj.put("id", "Send");
                            obj.put("x", location[0]);
                            obj.put("y", location[1]);
                            obj.put("width",payment_Button.getWidth());
                            obj.put("height", payment_Button.getHeight());

                            JSONObject payload = new JSONObject();
                            payload.put("screen", "MainActivity");
                            JSONArray buttons = new JSONArray();
                            buttons.put(obj);
                            payload.put("buttons", buttons);

                            Intent broadcastIntent = new Intent();
                            broadcastIntent.setAction("com.HelperApp_Prototype.ACTION_BUTTON_INFO");
                            broadcastIntent.setComponent(new ComponentName(
                                    "com.example.team7_realhelper",
                                    "com.example.team7_realhelper.MyReceiver"));
                            broadcastIntent.putExtra("payload", payload.toString());
                            Log.d("MainActivity", "전송된 정보: " + payload.toString());

                            sendBroadcast(broadcastIntent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
                }
                else if(isMenu(request)){
                    Log.d("MainActivity", "청구서 버튼 정보 요청 수신");
                    payment_Button.post(() -> {
                        try {
                            JSONObject obj = new JSONObject();
                            int[] location = new int[2];
                            menuButton.getLocationOnScreen(location);

                            obj.put("id", "Send");
                            obj.put("x", location[0]);
                            obj.put("y", location[1]);
                            obj.put("width",menuButton.getWidth());
                            obj.put("height", menuButton.getHeight());

                            JSONObject payload = new JSONObject();
                            payload.put("screen", "MainActivity");
                            JSONArray buttons = new JSONArray();
                            buttons.put(obj);
                            payload.put("buttons", buttons);

                            Intent broadcastIntent = new Intent();
                            broadcastIntent.setAction("com.HelperApp_Prototype.ACTION_BUTTON_INFO");
                            broadcastIntent.setComponent(new ComponentName(
                                    "com.example.team7_realhelper",
                                    "com.example.team7_realhelper.MyReceiver"));
                            broadcastIntent.putExtra("payload", payload.toString());
                            Log.d("MainActivity", "전송된 정보: " + payload.toString());

                            sendBroadcast(broadcastIntent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        }
    };

    private boolean isMenu(String request){
        boolean ismenu = false;
        for(int i = 0; i < menu.length; i++){
            if(request.equals(menu[i])) {
                ismenu = true;
                nextMenu = i;
                break;
            }

        }
        return ismenu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Oncreate", "실행1");
        IntentFilter filter = new IntentFilter("com.HelperApp_Prototype.ACTION_REQUEST_INFO");
        Log.d("Oncreate", "실행2");
        registerReceiver(requestInfoReceiver, filter, Context.RECEIVER_EXPORTED);
        Log.d("Oncreate", "실행3");

        ImageView payment_Button = findViewById(R.id.payment_Button);
        ImageButton menuButton = findViewById(R.id.imageButton);
        LinearLayout send_Button = findViewById(R.id.send_Button);


        Intent intentP = new Intent();
        intentP.setComponent(new ComponentName("com.example.team7_realhelper", "com.example.team7_realhelper.MainActivity"));
        intentP.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try {
            startActivity(intentP);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "앱을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
        }

/*-----------------------------------브로드캐스트:MainActivity-----------------------------------------*/


/*-----------------------------------클릭리스너:MainActivity-----------------------------------------*/
        payment_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QRActivity.class);
                startActivity(intent);
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                intent.putExtra("menuName", nextMenu);
                startActivity(intent);
            }
        });

        send_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, SelectActivity.class);
               startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent("com.example.ACTION_REMOVE_OVERLAY");
        intent.setPackage("com.example.team7_realhelper");
        sendBroadcast(intent);

        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 종료 시 필요한 작업 수행

        Intent intent = new Intent("com.example.ACTION_REMOVE_OVERLAY");
        intent.setPackage("com.example.team7_realhelper");  // B 앱 패키지명
        sendBroadcast(intent);
        unregisterReceiver(requestInfoReceiver);
    }


}