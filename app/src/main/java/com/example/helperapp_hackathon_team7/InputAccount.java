package com.example.helperapp_hackathon_team7;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class InputAccount extends AppCompatActivity {

    Button btn_selectBank;
    ImageButton btn_back;  // 다이얼로그 밖에 있는 뒤로가기 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_account);

        btn_selectBank = findViewById(R.id.btn_selectBank);
        btn_back = findViewById(R.id.btn_back);  // 이 버튼은 activity_input_account.xml 안에 있어야 함

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
