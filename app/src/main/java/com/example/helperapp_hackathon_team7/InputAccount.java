package com.example.helperapp_hackathon_team7;

import android.app.Dialog;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_account);

        btn_selectBank = findViewById(R.id.btn_selectBank);
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

                dlg.show();
            }
        });
    }
}