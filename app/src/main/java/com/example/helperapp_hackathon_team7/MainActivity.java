package com.example.helperapp_hackathon_team7;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intentP = new Intent();
        intentP.setComponent(new ComponentName("com.example.team7_realhelper", "com.example.team7_realhelper.MainActivity"));
        intentP.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intentP);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "앱을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
        }


        ImageView payment_Button = findViewById(R.id.payment_Button);
        ImageButton menuButton = findViewById(R.id.imageButton);
        LinearLayout send_Button = findViewById(R.id.send_Button);

/*-----------------------------------브로드캐스트:MainActivity-----------------------------------------*/
        payment_Button.post(() -> {
            try {
                JSONArray buttonArray = new JSONArray();
                View[] buttons = {payment_Button, menuButton, send_Button};
                String[] ids = {"Payment", "Menu", "Send"}; //결제, 메뉴, 송금

                for (int i = 0; i < buttons.length; i++) {
                    View btn = buttons[i];
                    int[] location = new int[2];
                    btn.getLocationOnScreen(location);

                    JSONObject obj = new JSONObject();
                    obj.put("id", ids[i]);
                    obj.put("x", location[0]);
                    obj.put("y", location[1]);
                    obj.put("width", btn.getWidth());
                    obj.put("height", btn.getHeight());
                    buttonArray.put(obj);
                }

                JSONObject payload = new JSONObject();
                payload.put("screen", "MainActivity");  // 화면 이름 설정
                payload.put("buttons", buttonArray);

                Intent intent = new Intent("com.HelperApp_Prototype.ACTION_BUTTON_INFO");
                intent.putExtra("payload", payload.toString());
                sendBroadcast(intent);

                Log.d("MainActivity 화면 정보 전송 완료.", "전송된 정보: " + payload.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

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
}