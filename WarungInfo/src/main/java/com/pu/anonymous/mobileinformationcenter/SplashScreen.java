package com.pu.anonymous.mobileinformationcenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class SplashScreen extends Activity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

    Thread Timer = new Thread() {
        public void run() {
            try {
                sleep(3500);

                startActivity(new Intent("com.pu.anonymous.mobileinformationcenter.Login"));
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }

        }
    };

    Timer.start();
}

    public void onBackPressed() {

    }
}
