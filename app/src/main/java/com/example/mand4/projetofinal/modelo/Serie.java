package com.example.mand4.projetofinal.modelo;

import java.util.List;

/**
 * Created by mand4 on 30/11/2017.
 */

public class Serie {

    private String name;
    private String overview;
    private String original_name;
    private double vote_average;
    private String first_air_date;
    private int runtime;
    private long id;
    private String poster_path;
    private String last_air_date;
    private List<Integer> genre_ids;

    public Serie(String name, String overview, String original_name, double vote_average, String first_air_date, int runtime, int id_filme, String poster_path, String last_air_date) {
        this.name = name;
        this.overview = overview;
        this.original_name = original_name;
        this.vote_average = vote_average;
        this.first_air_date = first_air_date;
        this.runtime = runtime;
        this.poster_path = poster_path;
        this.last_air_date = last_air_date;
    }

    public Serie() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }
}
