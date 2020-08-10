package com.example.qlbanhang.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlbanhang.R;

public class LoadApp extends AppCompatActivity {
    ImageView img_rotate1, img_Backgroud;
    TextView tv_Title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_app);
        setControl();
        setEvent();
    }

    private void setEvent() {
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_image);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        img_rotate1.setAnimation(animation1);
        tv_Title.setAnimation(animation2);
        AnimationDrawable drawable = (AnimationDrawable) img_Backgroud.getDrawable();
        drawable.start();
        CountDownTimer count = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                showActivity();
                finish();
            }
        }.start();
    }

    private void showActivity(){
        startActivity(new Intent(this, MainActivity.class));
    }

    private void setControl() {
        img_rotate1 = findViewById(R.id.img_cricle);
        img_Backgroud = findViewById(R.id.img_background);
        tv_Title = findViewById(R.id.tv_TieuDe);
    }
}