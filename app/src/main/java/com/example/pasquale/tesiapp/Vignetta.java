package com.example.pasquale.tesiapp;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class Vignetta implements Parcelable {


    private String picture;
    private String name;

    public Vignetta(){

    }

    public Vignetta(String picture, String name){
        this.name=name;
        this.picture=picture;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(picture);

    }

    public static final Parcelable.Creator<Vignetta> CREATOR =
            new Parcelable.Creator<Vignetta>() {
                public Vignetta createFromParcel(Parcel in) {
                    Vignetta vignetta = new Vignetta();
                    vignetta.name = in.readString();
                    vignetta.picture=in.readString();
                    return vignetta;
                }

                @Override
                public Vignetta[] newArray(int size) {
                    return new Vignetta[size];
                }
            };
}
