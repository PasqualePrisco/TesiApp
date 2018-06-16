package com.example.pasquale.tesiapp;



import java.util.ArrayList;
import java.util.List;

public class Album  {

    private String name;
    private List<Vignetta> vignette;

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

    public List<Vignetta> getVignette() {
        return vignette;
    }

    public void setVignette(List<Vignetta> vignette) {
        this.vignette = vignette;
    }



}
