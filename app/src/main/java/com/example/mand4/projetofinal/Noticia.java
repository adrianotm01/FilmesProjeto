package com.example.mand4.projetofinal;

import java.util.Date;

/**
 * Created by mand4 on 21/11/2017.
 */

public class Noticia {

    private String original_title;
    private String overview;
    private int imagem;
    private String name;
    private int id;
    public Noticia(String original_title, String overview, int imagem, String name, int id) {
        this.original_title = original_title;
        this.overview = overview;
        this.imagem = imagem;
        this.name = name;
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
