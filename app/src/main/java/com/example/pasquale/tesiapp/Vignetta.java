package com.example.pasquale.tesiapp;

import android.graphics.drawable.Drawable;

public class Vignetta {


    private Drawable picture;

    public Vignetta(Drawable picture){
        this.picture=picture;
    }

    public Drawable getPicture()
    {
        return picture;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }





}
