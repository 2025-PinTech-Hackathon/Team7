package com.example.helperapp_hackathon_team7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class MainAppReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String payloadStr = intent.getStringExtra("button_click");
        Log.d("MainAppReceiver", "수신완료" + payloadStr);

        if(payloadStr.equals("Send")){
            // team7 앱으로 응답 브로드캐스트 전송
            Intent responseIntent = new Intent("com.HelperApp_Prototype.ACTION_REQUEST_INFO");
            responseIntent.putExtra("result", "Send");
            context.sendBroadcast(responseIntent);

            Log.d("MainAppReceiver", "응답 브로드캐스트 보냄");

        }else if(payloadStr.equals("QR")){
            Intent responseIntent = new Intent("com.HelperApp_Prototype.ACTION_REQUEST_INFO");
            responseIntent.putExtra("result", "QR");
            context.sendBroadcast(responseIntent);

            Log.d("MainAppReceiver", "응답 브로드캐스트 보냄");
        }
        else if(payloadStr.equals("Bill")){
            Intent responseIntent = new Intent("com.HelperApp_Prototype.ACTION_REQUEST_INFO");
            responseIntent.putExtra("result", "Bill");
            context.sendBroadcast(responseIntent);

            Log.d("MainAppReceiver", "응답 브로드캐스트 보냄");
        }
        else if(payloadStr.equals("Oversea")){
            Intent responseIntent = new Intent("com.HelperApp_Prototype.ACTION_REQUEST_INFO");
            responseIntent.putExtra("result", "Oversea");
            context.sendBroadcast(responseIntent);

            Log.d("MainAppReceiver", "응답 브로드캐스트 보냄");
        }
        else if(payloadStr.equals("Membership")){
            Intent responseIntent = new Intent("com.HelperApp_Prototype.ACTION_REQUEST_INFO");
            responseIntent.putExtra("result", "Membership");
            context.sendBroadcast(responseIntent);

            Log.d("MainAppReceiver", "응답 브로드캐스트 보냄");
        }
        else if(payloadStr.equals("Gift")){
            Intent responseIntent = new Intent("com.HelperApp_Prototype.ACTION_REQUEST_INFO");
            responseIntent.putExtra("result", "Gift");
            context.sendBroadcast(responseIntent);

            Log.d("MainAppReceiver", "응답 브로드캐스트 보냄");
        }
        else if(payloadStr.equals("GoodDeal")){
            Intent responseIntent = new Intent("com.HelperApp_Prototype.ACTION_REQUEST_INFO");
            responseIntent.putExtra("result", "GoodDeal");
            context.sendBroadcast(responseIntent);

            Log.d("MainAppReceiver", "응답 브로드캐스트 보냄");
        }
        else if(payloadStr.equals("Near")){
            Intent responseIntent = new Intent("com.HelperApp_Prototype.ACTION_REQUEST_INFO");
            responseIntent.putExtra("result", "Near");
            context.sendBroadcast(responseIntent);

            Log.d("MainAppReceiver", "응답 브로드캐스트 보냄");
        }
    }
}
