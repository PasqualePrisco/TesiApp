package com.example.pasquale.tesiapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public ListView listView;
    public Button confirm;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.albumList);
        confirm=(Button)findViewById(R.id.confirm);

        customAdapter = new CustomAdapter(this, R.layout.list_layout, new ArrayList<Album>());

        listView.setAdapter(customAdapter);

        ArrayList<Vignetta> vignette=new ArrayList<>();
        Vignetta vignetta1=new Vignetta(getResources().getDrawable(R.drawable.vignetta));
        Vignetta vignetta2=new Vignetta(getResources().getDrawable(R.drawable.vignetta2));
        Vignetta vignetta3=new Vignetta(getResources().getDrawable(R.drawable.vignetta3));
        Vignetta vignetta4=new Vignetta(getResources().getDrawable(R.drawable.vignetta4));
        vignette.add(vignetta1);
        vignette.add(vignetta2);
        vignette.add(vignetta3);
        vignette.add(vignetta4);
        Album album= new Album("Dov'è il vaso",vignette);

        customAdapter.add(album);

    }

    public void startAlbum(View v){
        Intent intent = new Intent(getApplicationContext(),ActivitySlide.class);
        startActivity(intent);
    }

}
