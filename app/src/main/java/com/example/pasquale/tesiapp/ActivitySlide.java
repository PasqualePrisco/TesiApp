package com.example.pasquale.tesiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ActivitySlide extends Activity {

    public ImageView imgV;
    public ImageView imgNext;
    public ImageView imgBack;
    int temp=0;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);



        imgV = (ImageView)findViewById(R.id.vignetta);
        imgNext = (ImageView)findViewById(R.id.rightArrow);
        imgBack = (ImageView)findViewById(R.id.leftArrow);

        final ArrayList<Vignetta> vignetta = getIntent().getParcelableArrayListExtra("vignette");

        //###### get first picture path ######
        String path= vignetta.get(0).getPicture();

        //###### Load picture with Glide ######
        Glide.with(this).load(path).into(imgV);

        checkImgView();

        //###### The last two are the options ######
        size=vignetta.size() - 2;

        //###### next image button ######
        imgNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                temp++;
                if(temp==size){
                    Intent intent = new Intent(getApplicationContext(),ActivityChoice.class);
                    ArrayList<Vignetta> scelte= new ArrayList<>();
                    scelte.add(vignetta.get(size));
                    scelte.add(vignetta.get(size-1));
                    intent.putParcelableArrayListExtra("scelte", scelte);
                    startActivityForResult(intent,0);
                }
                else if(temp<size) {
                    Glide.with(getApplicationContext()).load(vignetta.get(temp).getPicture()).into(imgV);
                    checkImgView();
                }
            }
        });

        //###### back image button ######
          imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp>0) {
                    temp--;
                    Glide.with(getApplicationContext()).load(vignetta.get(temp).getPicture()).into(imgV);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0){
            temp=size-1;
        }
    }
}
