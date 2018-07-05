package com.example.pasquale.tesiapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

public class ActivityChoice extends AppCompatActivity {

    ImageView img1;
    ImageView img2;
//    ArrayList<? extends Vignetta> vignette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        img1=(ImageView)findViewById(R.id.vignetta1);
        img2=(ImageView)findViewById(R.id.vignetta2);

        //##### Array with the img #####
        final ArrayList<Vignetta> vignetta = getIntent().getParcelableArrayListExtra("scelte");

        //###### set result code ######
        setResult(0);

        //###### get first picture path0 ######
        String path0= vignetta.get(0).getPicture();
        String path1= vignetta.get(1).getPicture();


        //##### Generate random int #####
        Random random= new Random();
        int randCorr= random.nextInt();

        //##### the app needs to know in which position of the array the right image is located #####
        if(vignetta.get(0).getName().equals("correct") /*&& vignetta.get(1).getName().equals("wrong")*/){
            Log.d("", "onCreate: ------------------------------------------------------------> la prima posizione dell'array è corretta");

            //##### with even numbers the correct picture goes into img1 container otherwise in the img2 container
            if(randCorr%2==0) {
                Log.d("", "onCreate: ------------------------------------------------------------> numero pari quindi corretta in prima posizione");

                //##### Load picture with Glide #####
                Glide.with(this).load(path0).into(img1);
                Glide.with(this).load(path1).into(img2);
                //##### img1 is the correct choise #####
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCorrect();
                    }
                });
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showWrong();
                    }
                });

            }else{
                Log.d("", "onCreate: ------------------------------------------------------------> numero dispari quindi corretta in seconda posizione");

                //###### Load picture with Glide #####
                Glide.with(this).load(path0).into(img2);
                Glide.with(this).load(path1).into(img1);
                //##### img1 is the wrong choise #####
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showWrong();
                    }
                });
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCorrect();
                    }
                });
            }
        }else if(vignetta.get(0).getName().equals("wrong") /*&& vignetta.get(1).getName().equals("correct")*/){

            Log.d("", "onCreate: ------------------------------------------------------------> la prima posizione dell'array è sbagliata");
            //##### with even number the correct picture goes into img1 container otherwise in the img2 container
            if(randCorr%2==0) {
                Log.d("", "onCreate: ------------------------------------------------------------> numero pari quindi corretta in seconda posizione");

                //##### Load picture with Glide #####
                Glide.with(this).load(path0).into(img1);
                Glide.with(this).load(path1).into(img2);
                //##### img1 is the correct choise #####
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showWrong();
                    }
                });
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCorrect();
                    }
                });

            }else{
                Log.d("", "onCreate: ------------------------------------------------------------> numero dispari quindi corretta in prima posizione");

                //###### Load picture with Glide #####
                Glide.with(this).load(path0).into(img2);
                Glide.with(this).load(path1).into(img1);
                //##### img1 is the wrong choise #####
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCorrect();
                    }
                });
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showWrong();
                    }
                });
            }
        }
//        else{
//            //##### The app can't find a wrong or correct case between the image and it automatically send to the mainActivity
//
//            Log.e("Err", "---------------------------------------------------------->selezioni non corrette ");
//            Toast.makeText(getApplicationContext(),"The app as found a problem restart it or contact the doctor!",Toast.LENGTH_LONG).show();
//            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            startActivity(intent);
//        }
    }


    public void showCorrect() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        Toast.makeText(getApplicationContext(),"Corretto!",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(), ActivityAlbum.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(intent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }


        public void showWrong(){
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            //Yes button clicked
                            Toast.makeText(getApplicationContext(),"Sbagliato!",Toast.LENGTH_LONG).show();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}




