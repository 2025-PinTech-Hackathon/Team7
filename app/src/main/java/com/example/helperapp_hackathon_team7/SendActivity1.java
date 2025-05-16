package com.example.helperapp_hackathon_team7;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SendActivity1 extends AppCompatActivity {

    Button btnOkay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send1);

        btnOkay = findViewById(R.id.btn_okay);
/*-----------------------------------브로드캐스트:SendActivity1-----------------------------------------*/
        btnOkay.post(() -> {
            try {
                JSONArray buttonArray = new JSONArray();
                View[] buttons = {btnOkay};
                String[] ids = {"Okay"}; //확인

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
                payload.put("screen", "SendActivity1");  // 화면 이름 설정
                payload.put("buttons", buttonArray);

                Intent intent = new Intent("com.HelperApp_Prototype.ACTION_BUTTON_INFO_SEND1");
                intent.putExtra("payload", payload.toString());
                sendBroadcast(intent);

                Log.d("SendActivity1 화면 정보 전송 완료.", "전송된 정보: " + payload.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

/*-----------------------------------클릭리스너:SendActivity1-----------------------------------------*/
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendActivity1.this, SendActivity2.class);
                startActivity(intent);
            }
        });
    }
}