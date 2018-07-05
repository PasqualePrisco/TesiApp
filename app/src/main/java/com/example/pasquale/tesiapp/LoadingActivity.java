package com.example.pasquale.tesiapp;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class LoadingActivity extends AppCompatActivity {

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ImageView img= (ImageView)findViewById(R.id.img_loading);
        img.setBackgroundResource(R.drawable.animation_loading);
        animationDrawable= (AnimationDrawable)img.getBackground();
        animationDrawable.start();
    }

}
