package com.example.helperapp_hackathon_team7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SendActivity1 extends AppCompatActivity {

    Button btnOkay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send1);

        btnOkay = findViewById(R.id.btn_okay);
        btnOkay.post(() -> {
            int[] location = new int[2];
            btnOkay.getLocationOnScreen(location);

            int x = location[0];
            int y = location[1];
            int width = btnOkay.getWidth();
            int height = btnOkay.getHeight();

            //Log.d("AppA", "버튼 위치 및 크기 전송: x=" + x + ", y=" + y + ", w=" + width + ", h=" + height);

            // 브로드캐스트 인텐트 구성
            Intent intent = new Intent("com.example.ACTION_BUTTON_INFO");
            intent.putExtra("x", x);
            intent.putExtra("y", y);
            intent.putExtra("width", width);
            intent.putExtra("height", height);
            sendBroadcast(intent);
        });
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendActivity1.this, SendActivity2.class);
                startActivity(intent);
            }
        });
    }
}