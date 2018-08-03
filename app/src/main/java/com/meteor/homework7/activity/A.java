package com.meteor.homework7.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class A extends AppCompatActivity {
    private static final String KEY_MESSAGE_A = "key a";

    public static class CustomBroadcastReceiver extends BroadcastReceiver {                         //"static" is required for static register

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Toast.makeText(context.getApplicationContext(), intent.getStringExtra(KEY_MESSAGE_A), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                String tag="Homework7";
                Log.d(tag, e.getMessage());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }
}

