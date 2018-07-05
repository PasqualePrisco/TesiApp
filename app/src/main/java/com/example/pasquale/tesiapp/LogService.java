package com.example.pasquale.tesiapp;

import android.app.IntentService;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class LogService extends IntentService {

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private ArrayList<Album> albums;

    public LogService() {
        super("LogServices");
        mDatabase = FirebaseDatabase.getInstance();
        myRef= mDatabase.getReference();
        albums=new ArrayList<Album>();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int n=0;

        myRef.child("Albums").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ArrayList<Vignetta> vignette = new ArrayList<>();
                    Album album = ds.getValue(Album.class);
                    album.setName(ds.child("nome").getValue(String.class));
                    for (DataSnapshot dv : ds.child("vignette").getChildren()) {
                        Vignetta v = dv.getValue(Vignetta.class);
                        v.setName(dv.child("nome").getValue(String.class));
                        v.setPicture(dv.child("path").getValue(String.class));
                        vignette.add(v);
                    }
                    album.setVignette(vignette);
                    albums.add(album);

                }
                createIntent();
                onDestroy();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
    }
    public void createIntent(){
        Intent intent = new Intent(getApplicationContext(),ActivitySlide.class);
        intent.putParcelableArrayListExtra("vignette", (ArrayList<? extends Parcelable>) albums.get(0).getVignette());
        startActivity(intent);
    }
    public void onDestroy()
    {
        Log.i("PROVA SERVICE", "Distruzione Service");
    }
}
