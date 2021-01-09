package com.tth.gamebirdshooting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseGameActivity extends Activity {
    private static final String APP_NAME = "Bird_Shooting";
    private Button btn1, btn2, btn3, btn4, btn5;
    private SharedPreferences pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        pre = getSharedPreferences(APP_NAME, MODE_PRIVATE);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre.edit().putString("choose", "man1").apply();
                Intent intent = new Intent(ChooseGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre.edit().putString("choose", "man2").apply();
                Intent intent = new Intent(ChooseGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre.edit().putString("choose", "man3").apply();
                Intent intent = new Intent(ChooseGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre.edit().putString("choose", "man4").apply();
                Intent intent = new Intent(ChooseGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pre.edit().putString("choose", "man5").apply();
                Intent intent = new Intent(ChooseGameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}