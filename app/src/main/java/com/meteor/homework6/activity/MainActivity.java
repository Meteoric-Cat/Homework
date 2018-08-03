package com.meteor.homework6.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnCallA, btnCallSubB;

    private static final String ACTION_A = "com.meteor.homework7.CALL_A";
    private static final String PACKAGE_A = "com.meteor.homework7.activity";
    private static final String CLASS_A = "com.meteor.homework7.activity.A$CustomBroadcastReceiver";
    private static final String KEY_MESSAGE_A = "key a";
    private static final String MESSAGE_A = "Xin chào A";
    private static final String ACTION_B = "com.meteor.homework8.CALL_SUB_B";
    private static final String PACKAGE_B = "com.meteor.homework8.activity";
    private static final String CLASS_B = "com.meteor.homework8.activity.SubB$CustomBroadcastReceiver";
    private static final String KEY_MESSAGE_B = "key b";
    private static final String MESSAGE_B = "Xin chào B";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUIViews();
        initUIListeners();
    }

    private void initUIViews() {
        btnCallA = findViewById(R.id.btn_callA);
        btnCallSubB = findViewById(R.id.btn_callSubB);
    }

    private void initUIListeners() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_callA:
                        callA();
                        break;
                    case R.id.btn_callSubB:
                        callSubB();
                        break;
                }
            }
        };

        btnCallA.setOnClickListener(onClickListener);
        btnCallSubB.setOnClickListener(onClickListener);
    }

    private void callA() {
        BroadTask broadTask=new BroadTask();
        broadTask.execute();

        Intent startIntent = getPackageManager().getLaunchIntentForPackage(PACKAGE_A);
        startIntent.setAction(Intent.ACTION_VIEW);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startIntent);

        //solution2-no asyntask
        /*
        Intent intent = new Intent();
        intent.setAction(ACTION_A);
        intent.setComponent(new ComponentName(PACKAGE_A, CLASS_A));
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.putExtra(KEY_MESSAGE_A, MESSAGE_A);
        sendBroadcast(intent);
        */
    }

    private void callSubB() {
        Intent intent = new Intent();
        intent.setAction(ACTION_B);
        intent.setComponent(new ComponentName(PACKAGE_B, CLASS_B));
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(KEY_MESSAGE_B, MESSAGE_B);
        sendBroadcast(intent);
    }

    public class BroadTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent();
            intent.setAction(ACTION_A);
            intent.setComponent(new ComponentName(PACKAGE_A, CLASS_A));
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(KEY_MESSAGE_A, MESSAGE_A);
            sendBroadcast(intent);

            return null;
        }
    }
}
