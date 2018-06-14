package com.example.pasquale.tesiapp;

import java.util.ArrayList;

public class Album {

    private String title;
    private ArrayList<Vignetta> vignette;

    public Album(String title, ArrayList<Vignetta> vignette){
        this.title=title;
        this.vignette=vignette;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Vignetta> getVignette() {
        return vignette;
    }

    public void setVignette(ArrayList<Vignetta> vignette) {
        this.vignette = vignette;
    }
}
