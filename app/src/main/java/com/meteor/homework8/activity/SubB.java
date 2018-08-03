package com.meteor.homework8.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.lang.annotation.Inherited;

public class SubB extends AppCompatActivity {
    private static final String KEY_MESSAGE_B = "key b";

    public static class CustomBroadcastReceiver extends BroadcastReceiver {
        public CustomBroadcastReceiver(){
            super();
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            //Toast.makeText(context.getApplicationContext(), intent.getStringExtra(KEY_MESSAGE_B),Toast.LENGTH_SHORT).show();
            intent.setClass(context.getApplicationContext(), SubB.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_b);
    }

    @Override
    protected void onStart(){
        super.onStart();
        //show message here
    }
}
