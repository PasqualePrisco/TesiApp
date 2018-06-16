package com.example.pasquale.tesiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    public ListView listView;
    public Button confirm;
    public List<Album> albums =new ArrayList<>();
    public int temp;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance();
        myRef= mDatabase.getReference();

        listView = (ListView)findViewById(R.id.albumList);
        confirm=(Button)findViewById(R.id.confirm);

        customAdapter = new CustomAdapter(this, R.layout.list_layout, new ArrayList<Album>());

        listView.setAdapter(customAdapter);

        //######## Get albums from DataBase ########
        myRef.child("Albums").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                if (customAdapter != null) {
                    customAdapter.clear();
                }
                List<Vignetta> vignette = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Album album = ds.getValue(Album.class);
                    album.setName(ds.child("nome").getValue(String.class));
                    for (DataSnapshot dv : ds.child("vignette").getChildren()) {
                        Vignetta v = dv.getValue(Vignetta.class);
                        v.setName(dv.child("nome").getValue(String.class));
                        v.setPicture(dv.child("path").getValue(String.class));
                        vignette.add(v);
                    }
                    album.setVignette(vignette);
                    customAdapter.add(album);
                    albums.add(album);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
            });



        //###### Listner to element for get position ######
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Show Toast
              temp=position;
        }
        });

    }


    public void startAlbum(View v){
        Intent intent = new Intent(getApplicationContext(),ActivitySlide.class);

        intent.putParcelableArrayListExtra("vignette", (ArrayList<? extends Parcelable>)  albums.get(temp).getVignette());

        startActivity(intent);
    }


}
