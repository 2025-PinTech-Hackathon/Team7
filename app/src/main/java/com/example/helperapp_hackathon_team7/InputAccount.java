package com.example.helperapp_hackathon_team7;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InputAccount extends AppCompatActivity {

    LinearLayout accountNum,bankSelect;
    Button btn_selectBank, btn_okay;
    ImageButton btn_back;  // 다이얼로그 밖에 있는 뒤로가기 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_account);

        btn_selectBank = findViewById(R.id.btn_selectBank);
        btn_back = findViewById(R.id.btn_back);
        accountNum = findViewById(R.id.account_num);
        bankSelect = findViewById(R.id.bank_select);
        btn_okay = findViewById(R.id.btn_okay);

/*-----------------------------------브로드캐스트:InputAccount-----------------------------------------*/
        btn_okay.post(() -> {
            try {
                JSONArray buttonArray = new JSONArray();
                View[] buttons = {accountNum, bankSelect, btn_okay};
                String[] ids = {"Account Number Input", "Select Bank", "Okay"}; //계좌번호, 은행/증권사, 확인

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
                payload.put("screen", "InputAccount");  // 화면 이름 설정
                payload.put("buttons", buttonArray);

                Intent intent = new Intent("com.HelperApp_Prototype.ACTION_BUTTON_INFO_INPUTACCOUNT");
                intent.putExtra("payload", payload.toString());
                sendBroadcast(intent);

                Log.d("InputAccount 화면 정보 전송 완료.", "전송된 정보: " + payload.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

/*-----------------------------------클릭리스너:InputAccount-----------------------------------------*/
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputAccount.this, SendActivity1.class);
                startActivity(intent);
            }
        });

        // 뒤로가기 버튼 클릭 시
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputAccount.this, SelectActivity.class);
                startActivity(intent);
                finish(); // 현재 액티비티 종료 (원한다면)
            }
        });

        // 은행 선택 버튼 클릭 시 다이얼로그 생성
        btn_selectBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dlg = new Dialog(InputAccount.this);
                dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dlg.setContentView(R.layout.dialog_input_account);

                Window window = dlg.getWindow();
                if (window != null) {
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setGravity(Gravity.BOTTOM);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                ImageButton dlg_btn_bank = dlg.findViewById(R.id.dialog_btn_bank);
                dlg_btn_bank.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dlg.dismiss();
                    }
                });

                Button dialog_exit_Button = dlg.findViewById(R.id.dialog_exit_Button);
                dialog_exit_Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dlg.dismiss();
                    }
                });

                dlg.show();
            }
        });
    }
}
