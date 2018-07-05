package com.example.pasquale.tesiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static android.content.ContentValues.TAG;

public class EmotionActivity extends AppCompatActivity {

    ImageView img;
    Button leftB;
    Button rightB;
    private ArrayList<Emozione> emozioni= new ArrayList<Emozione>();
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    int count;
    int corrette;
    String[] errorEmotion={"rabbia","tristezza","felicità","paura"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion);

        mDatabase = FirebaseDatabase.getInstance();
        myRef= mDatabase.getReference();

        count=0;
        img=(ImageView)findViewById(R.id.img_emotion);
        leftB=(Button)findViewById(R.id.leftButton);
        rightB=(Button)findViewById(R.id.rightButton);

        myRef.child("Emozioni").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Emozione emozione = ds.getValue(Emozione.class);
                    emozione.setNome(ds.child("nome").getValue(String.class));
                    emozione.setPath(ds.child("path").getValue(String.class));
                    emozioni.add(emozione);
                }
                Glide.with(getApplicationContext()).load(emozioni.get(count).getPath()).into(img);
                setButton();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

    }



    public void verify(View v){
        Button b=(Button) v;
        if(b.getText().equals(emozioni.get(count).getNome())){
            corrette++;
        }
        count++;
        if(count<emozioni.size()) {
            Glide.with(this).load(emozioni.get(count).getPath()).into(img);
            setButton();
        }
        else{
            Toast.makeText(this, "Risposte corrette"+corrette+" su"+emozioni.size(),Toast.LENGTH_LONG).show();
        }
    }

    public void setButton(){
        Random random= new Random();
        int rand= random.nextInt();
        int i= random.nextInt(4);
        if(rand%2==0){
            leftB.setText(emozioni.get(count).getNome());
            String em=errorEmotion[i];
            if(em.equals(emozioni.get(count).getNome())){
                if(i>=0 && i!=4) {
                    em = errorEmotion[i + 1];
                }
                else{
                    em=errorEmotion[i-1];
                }
            }
            rightB.setText(em);
        }
        else {
            rightB.setText(emozioni.get(count).getNome());
            String em=errorEmotion[i];
            if(em.equals(emozioni.get(count).getNome())){
                if(i>=0 && i!=4) {
                    em = errorEmotion[i + 1];
                }
                else{
                    em=errorEmotion[i-1];
                }
            }
            leftB.setText(em);
        }
    }

}
