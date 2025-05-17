package com.example.helperapp_hackathon_team7;

import android.content.ComponentName;
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

        Intent intent = getIntent();
        boolean guideOn = intent.getBooleanExtra("guideOn", false);

        btnOkay = findViewById(R.id.btn_okay);
/*-----------------------------------브로드캐스트:SendActivity1-----------------------------------------*/
        if(guideOn){
            btnOkay.post(() -> {
                try{
                    JSONObject obj = new JSONObject();
                    int[] location = new int[2];
                    btnOkay.getLocationOnScreen(location);

                    obj.put("id", "Account_Input");
                    obj.put("x", location[0]);
                    obj.put("y", location[1]);
                    obj.put("width", btnOkay.getWidth());
                    obj.put("height", btnOkay.getHeight());

                    JSONObject payload = new JSONObject();
                    payload.put("screen", "SendActivity1");
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

/*-----------------------------------클릭리스너:SendActivity1-----------------------------------------*/
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendActivity1.this, SendActivity2.class);
                intent.putExtra("guideOn", guideOn);
                startActivity(intent);
            }
        });
    }
}