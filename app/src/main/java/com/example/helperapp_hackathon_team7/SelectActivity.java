package com.example.helperapp_hackathon_team7;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_account);

        Intent intent = getIntent();
        boolean guideOn = intent.getBooleanExtra("guideOn", false);

        //button_account_input
        Button button_account_input = findViewById(R.id.button_account_input);
        //backBtn
        ImageButton backBtn = findViewById(R.id.backBtn);


/*-----------------------------------브로드캐스트:SelectActivity-----------------------------------------*/
        if(guideOn) {
            button_account_input.post(() -> {
                try {
                    JSONObject obj = new JSONObject();
                    int[] location = new int[2];
                    button_account_input.getLocationOnScreen(location);

                    obj.put("id", "Account_Input");
                    obj.put("x", location[0]);
                    obj.put("y", location[1]);
                    obj.put("width", button_account_input.getWidth());
                    obj.put("height", button_account_input.getHeight());

                    JSONObject payload = new JSONObject();
                    payload.put("screen", "SelectActivity");
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
/*-----------------------------------클릭리스너:SelectActivity-----------------------------------------*/
        button_account_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, InputAccount.class);
                intent.putExtra("guideOn", guideOn);
                startActivity(intent);
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}
