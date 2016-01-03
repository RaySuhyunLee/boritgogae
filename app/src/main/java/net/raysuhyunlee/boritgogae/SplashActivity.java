package net.raysuhyunlee.boritgogae;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindString;

/**
 * Created by SuhyunLee on 2015. 12. 13..
 */
public class SplashActivity extends AppCompatActivity {

    final int DELAY_IN_MILLIS = 1700;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if(isFirstTime()) {
                openFirstTimeActivity();
            } else {
                openMainActivity();
            }
        }, DELAY_IN_MILLIS);
    }

    public boolean isFirstTime() {
        SharedPreferences pref = getSharedPreferences(API.PREF_NAME, MODE_PRIVATE);
        Boolean isFirstTime = pref.getBoolean(API.PREF_KEY_IS_FIRST_TIME, true);
        return isFirstTime;
    }

    public void openFirstTimeActivity() {
        Intent intent = new Intent(this, FirstTimeActivity.class);
        startActivity(intent);
        finish();
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
