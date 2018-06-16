package com.example.pasquale.tesiapp;



import java.util.ArrayList;


public class Album  {

    private String name;
    private ArrayList<Vignetta> vignette;

    public Album(){
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }


    public Album(String name, ArrayList<Vignetta> vignette){
        this.name=name;
        this.vignette=vignette;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Vignetta> getVignette() {
        return vignette;
    }

    public void setVignette(ArrayList<Vignetta> vignette) {
        this.vignette = vignette;
    }



}
