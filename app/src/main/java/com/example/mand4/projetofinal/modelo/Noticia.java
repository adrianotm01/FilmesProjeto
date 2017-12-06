package com.example.mand4.projetofinal.modelo;

import java.util.List;

/**
 * Created by mand4 on 21/11/2017.
 */

public class Noticia {

    private String title;
    private String overview;
    private int imagem;
    private String original_title;
    private double vote_average;
    private String release_date;
    private int runtime;
    private String backdrop_path;
    private int id_filme;
    private long id;
    private String poster_path;
    private int revenue;
    private List<Integer> genre_ids;

    public Noticia(String title, String overview, int imagem, String original_title, double vote_average, String release_date, int runtime) {
        this.title = title;
        this.overview = overview;
        this.imagem = imagem;
        this.original_title = original_title;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.runtime = runtime;
        this.id = 0;
    }

    public Noticia() {

    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }
    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getId_filme() {
        return id_filme;
    }

    public void setId_filme(int id_filme) {
        this.id_filme = id_filme;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}
