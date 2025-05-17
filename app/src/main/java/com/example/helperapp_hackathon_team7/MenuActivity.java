package com.example.helperapp_hackathon_team7;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity {

    LinearLayout btnBill, btnOverseas, btnMembership, btnGift, btnGoodDeal, btnQRpay, btnNear;
    boolean guideOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        Intent menuIntent = getIntent();
        int menu = menuIntent.getIntExtra("menuName", 0);
        guideOn = menuIntent.getBooleanExtra("guideOn", false);


        btnBill = findViewById(R.id.btn_bill);
        btnOverseas = findViewById(R.id.btn_foreign);
        btnMembership = findViewById(R.id.btn_membership);
        btnGift = findViewById(R.id.btn_pay);
        btnGoodDeal = findViewById(R.id.btn_gooddeal);
        btnQRpay = findViewById(R.id.btn_offline);
        btnNear = findViewById(R.id.btn_nearby);

        ImageButton back_Button = findViewById(R.id.back_button);
        View[] btns = {btnBill, btnOverseas, btnMembership, btnGift, btnGoodDeal, btnNear};
/*-----------------------------------브로드캐스트:MenuActivity-----------------------------------------*/

        if(guideOn){
            btns[menu].post(() -> {
                try{
                    JSONObject obj = new JSONObject();
                    int[] location = new int[2];
                    btns[menu].getLocationOnScreen(location);

                    obj.put("id", "Account_Input");
                    obj.put("x", location[0]);
                    obj.put("y", location[1]);
                    obj.put("width", btns[menu].getWidth());
                    obj.put("height", btns[menu].getHeight());

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
                    Log.d("MenuActivity", "전송된 정보: " + payload.toString());

                    sendBroadcast(broadcastIntent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        }

/*-----------------------------------클릭리스너:MenuActivity-----------------------------------------*/
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guideOn = false;
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("guideOn", guideOn);
                startActivity(intent);
            }
        });



    }
}
