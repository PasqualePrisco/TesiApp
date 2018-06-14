package com.example.pasquale.tesiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ActivitySlide extends Activity {

    public ImageView imgV;
    public ImageView imgNext;
    public ImageView imgBack;
    ArrayList<Vignetta> vignette;
    int temp=0;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        imgV = (ImageView)findViewById(R.id.vignetta);
        imgNext = (ImageView)findViewById(R.id.rightArrow);
        imgBack = (ImageView)findViewById(R.id.leftArrow);

        vignette=new ArrayList<>();
        Vignetta vignetta1=new Vignetta(getResources().getDrawable(R.drawable.vignetta));
        Vignetta vignetta2=new Vignetta(getResources().getDrawable(R.drawable.vignetta2));
        Vignetta vignetta3=new Vignetta(getResources().getDrawable(R.drawable.vignetta3));
        Vignetta vignetta4=new Vignetta(getResources().getDrawable(R.drawable.vignetta4));
        vignette.add(vignetta1);
        vignette.add(vignetta2);
        vignette.add(vignetta3);
        vignette.add(vignetta4);
        size=vignette.size();

        imgV.setImageDrawable(vignette.get(temp).getPicture());
        checkImgView();
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp++;
                if(temp==size){
                    temp=50;
                    Intent intent = new Intent(getApplicationContext(),activityChoose.class);
                    startActivity(intent);
                }
                else if(temp<size) {
                    imgV.setImageDrawable(vignette.get(temp).getPicture());
                    checkImgView();
                }

                if(temp>=50){
                    temp=size-1;
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp>0) {
                    temp--;
                    imgV.setImageDrawable(vignette.get(temp).getPicture());
                    checkImgView();
                }
            }
        });

    }


    public void checkImgView(){
        if(temp==0) {
            imgBack.setVisibility(View.INVISIBLE);
        }
        else if(temp==1){
            imgBack.setVisibility(View.VISIBLE);
        }
    }


}
