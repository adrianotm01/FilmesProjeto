package com.example.mand4.projetofinal.modelo;

/**
 * Created by mand4 on 25/11/2017.
 */

public class Genero {
    private String name;
    private int id;

    public Genero(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Genero() {

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
