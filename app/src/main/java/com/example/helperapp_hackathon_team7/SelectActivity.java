package com.example.helperapp_hackathon_team7;

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

        //button_account_input
        Button button_account_input = findViewById(R.id.button_account_input);
        //backBtn
        ImageButton backBtn = findViewById(R.id.backBtn);


/*-----------------------------------브로드캐스트:SelectActivity-----------------------------------------*/
        button_account_input.post(() -> {
            try {
                JSONArray buttonArray = new JSONArray();
                View[] buttons = {button_account_input};
                String[] ids = {"Bill", "Overseas", "Membership", "Gift", "GoodDeal", "QRpay", "Near"}; //결제, 메뉴, 송금

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
                payload.put("screen", "SelectActivity");  // 화면 이름 설정
                payload.put("buttons", buttonArray);

                Intent intent = new Intent("com.HelperApp_Prototype.ACTION_BUTTON_INFO_SELECT");
                intent.putExtra("payload", payload.toString());
                sendBroadcast(intent);

                Log.d("SelectActivity 화면 정보 전송 완료.", "전송된 정보: " + payload.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

/*-----------------------------------클릭리스너:SelectActivity-----------------------------------------*/
        button_account_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, InputAccount.class);
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
