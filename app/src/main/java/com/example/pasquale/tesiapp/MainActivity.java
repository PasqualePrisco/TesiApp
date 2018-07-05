package com.example.pasquale.tesiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

   TextView txtAlbum;
   TextView txtSequence;
   TextView txtEmotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAlbum = (TextView) findViewById(R.id.txtAlbum);
        txtSequence = (TextView) findViewById(R.id.txtSequence);
        txtEmotion = (TextView) findViewById(R.id.txtEmotion);


        txtAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ActivityAlbum.class);
                startActivity(intent);
            }
        });

        txtEmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), EmotionActivity.class);
                startActivity(intent);
            }
        });


    }


}
