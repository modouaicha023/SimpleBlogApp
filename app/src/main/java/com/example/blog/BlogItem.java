package com.example.blog;

public class BlogItem {
    private final String titre;
    private final String text;
    private final String auteur;
    private final String date;


    public String getTitre() {
        return titre;
    }



    public String getText() {
        return text;
    }


    public String getAuteur() {
        return auteur;
    }

    public String getDate() {
        return date;
    }

    public BlogItem(String auteur,String titre, String text,  String date) {
        this.auteur = auteur;
        this.titre = titre;
        this.text = text;
        this.date = date;
    }
}
