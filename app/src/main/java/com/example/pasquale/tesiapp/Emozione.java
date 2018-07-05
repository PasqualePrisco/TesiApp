package com.example.pasquale.tesiapp;

public class Emozione {

    private String nome;
    private String path;


    public Emozione(){
    }

    public Emozione(String nome, String path){
        this.nome=nome;
        this.path=path;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
