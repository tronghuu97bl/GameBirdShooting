package com.tth.gamebirdshooting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        System.out.println("pause");
        super.onPause();
        onDestroy();
    }

    @Override
    protected void onDestroy() {
        System.out.println("destroty");

        super.onDestroy();
    }
}