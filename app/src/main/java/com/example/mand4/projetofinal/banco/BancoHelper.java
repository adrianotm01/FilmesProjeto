package com.example.mand4.projetofinal.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mand4.projetofinal.modelo.Genero;
import com.example.mand4.projetofinal.modelo.Noticia;
import com.example.mand4.projetofinal.modelo.Serie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mand4 on 21/11/2017.
 */

public class BancoHelper extends SQLiteOpenHelper {
    private String texto = " TEXT";
    private String real = "REAL";

    private final String criar_Tabela = ("CREATE TABLE "+ NoticiaContrato.NoticiaEntry.tabela+"("+ NoticiaContrato.NoticiaEntry._ID
            +" INTEGER PRIMARY KEY, "+ NoticiaContrato.NoticiaEntry.title+texto+", "+ NoticiaContrato.NoticiaEntry.overview+
            texto+","+ NoticiaContrato.NoticiaEntry.runtime+" INTEGER"+", "+NoticiaContrato.NoticiaEntry.original_title+texto+", "
            + NoticiaContrato.NoticiaEntry.data+" "+texto+", "+ NoticiaContrato.NoticiaEntry.nota+" "+real+", "+
            NoticiaContrato.NoticiaEntry.idFilme+" INTEGER, "+ NoticiaContrato.NoticiaEntry.poster+texto+","+
            NoticiaContrato.NoticiaEntry.backdrop+texto+","+ NoticiaContrato.NoticiaEntry.orcamento+" INTEGER);");

    private final String criar_TabelaSerie = ("CREATE TABLE "+ SerieContrato.SerieEntry.tabela+"("+ SerieContrato.SerieEntry._ID
            +" INTEGER PRIMARY KEY, "+ SerieContrato.SerieEntry.nomeserie+texto+", "+ SerieContrato.SerieEntry.overview+
            texto+","+ SerieContrato.SerieEntry.runtime+" INTEGER"+", "+ SerieContrato.SerieEntry.original_name+texto+", "
            + SerieContrato.SerieEntry.first_air_date+texto+", "+ SerieContrato.SerieEntry.vote_average+" "+real+", "+
            SerieContrato.SerieEntry.id_serie+" INTEGER, "+ SerieContrato.SerieEntry.poster_path+texto+","+
            SerieContrato.SerieEntry.last_air_date+texto+");");

    private final String criar_TabelaMaisVotados = ("CREATE TABLE "+ NoticiaContrato.NoticiaEntry.tabela+"MaisVotados("
            + NoticiaContrato.NoticiaEntry._ID+" INTEGER PRIMARY KEY, "+ NoticiaContrato.NoticiaEntry.title+texto+", "
            + NoticiaContrato.NoticiaEntry.overview+texto+","+ NoticiaContrato.NoticiaEntry.runtime+texto+", "
            +NoticiaContrato.NoticiaEntry.original_title+texto+", "+ NoticiaContrato.NoticiaEntry.data+" "+texto+", "
            + NoticiaContrato.NoticiaEntry.nota+" "+real+", "+NoticiaContrato.NoticiaEntry.idFilme+" INTEGER, "
            + NoticiaContrato.NoticiaEntry.poster+texto +NoticiaContrato.NoticiaEntry.backdrop+texto+","
            + NoticiaContrato.NoticiaEntry.orcamento+" INTEGER);");

    private final String criar_TabelaMaisVotadosSerie = ("CREATE TABLE "+ SerieContrato.SerieEntry.tabela+"MaisVotados("
            + SerieContrato.SerieEntry._ID+" INTEGER PRIMARY KEY, "+ SerieContrato.SerieEntry.nomeserie+texto+", "
            + SerieContrato.SerieEntry.overview+texto+","+ SerieContrato.SerieEntry.runtime+texto+", "
            +SerieContrato.SerieEntry.original_name+texto+", "+ SerieContrato.SerieEntry.first_air_date+" "+texto+", "
            + SerieContrato.SerieEntry.vote_average+" "+real+", "+ SerieContrato.SerieEntry.id_serie+" INTEGER, "
            + SerieContrato.SerieEntry.poster_path+texto+");");

    private final String criarTabelaGenero = ("CREATE TABLE "+ GeneroContrato.GeneroEntry.tabela+
            "("+GeneroContrato.GeneroEntry.id_genero+" INTEGER PRIMARY KEY,"+ GeneroContrato.GeneroEntry.nome+texto+");");

    public BancoHelper(Context context){
        super(context,"projeto_final",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(criar_Tabela);
        sqLiteDatabase.execSQL(criar_TabelaMaisVotados);
        sqLiteDatabase.execSQL(criarTabelaGenero);
        sqLiteDatabase.execSQL(criar_TabelaSerie);
        sqLiteDatabase.execSQL(criar_TabelaMaisVotadosSerie);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public List<Noticia> listar(){
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.query(NoticiaContrato.NoticiaEntry.tabela,null,null,null,null,null,null);
            return findAll(c);
        }finally {
            db.close();
        }
    }

    public List<Genero> listarGeneros(){
        SQLiteDatabase db = getReadableDatabase();
        try{
            Cursor cursor = db.query(GeneroContrato.GeneroEntry.tabela,null,null,null,null,null,null);
            return encontrarGeneros(cursor);
        }
        finally {
            db.close();
        }

    }

    public List<Noticia> listarMaisVotados(){
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.query(NoticiaContrato.NoticiaEntry.tabela+"MaisVotados",null,null,null,null,null,null);
            return findAll(c);
        }finally {
            db.close();
        }
    }
    public List<Serie> listarSeries() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c;
        try{
            c = db.query(SerieContrato.SerieEntry.tabela,null,null,null,null,null,null,null);
            return encontrarSeries(c);
        }finally {
            db.close();
        }

    }

    private List<Noticia> findAll(Cursor c) {
        List<Noticia> noticias = new ArrayList<>();
            if (c.moveToFirst()) {
                do {
                    Noticia not = new Noticia();
                    not.setTitle(c.getString(c.getColumnIndex(NoticiaContrato.NoticiaEntry.title)));
                    not.setOriginal_title(c.getString(c.getColumnIndex(NoticiaContrato.NoticiaEntry.original_title)));
                    not.setRelease_date(c.getString(c.getColumnIndex(NoticiaContrato.NoticiaEntry.data)));
                    not.setId_filme(c.getInt(c.getColumnIndex(NoticiaContrato.NoticiaEntry.idFilme)));
                    not.setOverview(c.getString(c.getColumnIndexOrThrow(NoticiaContrato.NoticiaEntry.overview)));
                    not.setRuntime(c.getInt(c.getColumnIndexOrThrow(NoticiaContrato.NoticiaEntry.runtime)));
                    not.setId(c.getLong(c.getColumnIndexOrThrow(NoticiaContrato.NoticiaEntry._ID)));
                    not.setVote_average(c.getDouble(c.getColumnIndexOrThrow(NoticiaContrato.NoticiaEntry.nota)));
                    not.setPoster_path(c.getString(c.getColumnIndex(NoticiaContrato.NoticiaEntry.poster)));
                    not.setBackdrop_path(c.getString(c.getColumnIndex(NoticiaContrato.NoticiaEntry.backdrop)));
                    not.setRevenue(c.getInt(c.getColumnIndexOrThrow(NoticiaContrato.NoticiaEntry.orcamento)));
                    noticias.add(not);
                } while (c.moveToNext());
            }
            return noticias;
    }

    public List<Genero> encontrarGeneros(Cursor cursor) {
        List<Genero> generos = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Genero genero = new Genero();
                genero.setName(cursor.getString(cursor.getColumnIndexOrThrow(GeneroContrato.GeneroEntry.nome)));
                genero.setId(cursor.getInt(cursor.getColumnIndexOrThrow(GeneroContrato.GeneroEntry.id_genero)));
                generos.add(genero);
            }while (cursor.moveToNext());
        }
        return generos;
    }

    public List<Serie> encontrarSeries(Cursor c){
        List<Serie> lista = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Serie not = new Serie();
                not.setName(c.getString(c.getColumnIndex(SerieContrato.SerieEntry.nomeserie)));
                not.setOriginal_name(c.getString(c.getColumnIndex(SerieContrato.SerieEntry.original_name)));
                not.setFirst_air_date(c.getString(c.getColumnIndex(SerieContrato.SerieEntry.first_air_date)));
                not.setId(c.getInt(c.getColumnIndex(SerieContrato.SerieEntry.id_serie)));
                not.setOverview(c.getString(c.getColumnIndexOrThrow(SerieContrato.SerieEntry.overview)));
                not.setRuntime(c.getInt(c.getColumnIndexOrThrow(SerieContrato.SerieEntry.runtime)));
                not.setVote_average(c.getDouble(c.getColumnIndexOrThrow(SerieContrato.SerieEntry.vote_average)));
                not.setPoster_path(c.getString(c.getColumnIndex(SerieContrato.SerieEntry.poster_path)));
                lista.add(not);
                Log.i("adri",not.getName());
            } while (c.moveToNext());
        }
        return lista;
    }

    public void inserirFilmes(List<Noticia> list){
        SQLiteDatabase db = getWritableDatabase();
        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(NoticiaContrato.NoticiaEntry.title, list.get(i).getTitle());
                cv.put(NoticiaContrato.NoticiaEntry.overview, list.get(i).getOverview());
                cv.put(NoticiaContrato.NoticiaEntry.original_title, list.get(i).getOriginal_title());
                cv.put(NoticiaContrato.NoticiaEntry.data, list.get(i).getRelease_date());
                cv.put(NoticiaContrato.NoticiaEntry.nota, list.get(i).getVote_average());
                cv.put(NoticiaContrato.NoticiaEntry.idFilme, list.get(i).getId());
                cv.put(NoticiaContrato.NoticiaEntry.poster,list.get(i).getPosterPath());
                cv.put(NoticiaContrato.NoticiaEntry.runtime,list.get(i).getRuntime());
                cv.put(NoticiaContrato.NoticiaEntry.backdrop,list.get(i).getBackdrop_path());
                cv.put(NoticiaContrato.NoticiaEntry.orcamento,list.get(i).getRevenue());
                db.insert(NoticiaContrato.NoticiaEntry.tabela, null, cv);
            }
        }finally {
            db.close();
        }
    }

    public void inserirMaisVotados(List<Noticia> list){
        SQLiteDatabase db = getWritableDatabase();
        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(NoticiaContrato.NoticiaEntry.title, list.get(i).getTitle());
                cv.put(NoticiaContrato.NoticiaEntry.overview, list.get(i).getOverview());
                cv.put(NoticiaContrato.NoticiaEntry.original_title, list.get(i).getOriginal_title());
                cv.put(NoticiaContrato.NoticiaEntry.data, list.get(i).getRelease_date());
                cv.put(NoticiaContrato.NoticiaEntry.nota, list.get(i).getVote_average());
                cv.put(NoticiaContrato.NoticiaEntry.idFilme, list.get(i).getId());
                cv.put(NoticiaContrato.NoticiaEntry.poster,list.get(i).getPosterPath());
                cv.put(NoticiaContrato.NoticiaEntry.backdrop,list.get(i).getBackdrop_path());
                cv.put(NoticiaContrato.NoticiaEntry.orcamento,list.get(i).getRevenue());
                db.insert(NoticiaContrato.NoticiaEntry.tabela+"MaisVotados", null, cv);
            }
        }finally {
            db.close();
        }
    }

    public void inserirGenero(List<Genero> generoList){
        SQLiteDatabase db = getWritableDatabase();
        try {
            for (int i = 0; i < generoList.size() ; i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("nome",generoList.get(i).getName());
                contentValues.put(GeneroContrato.GeneroEntry.id_genero,generoList.get(i).getId());
                db.insert(GeneroContrato.GeneroEntry.tabela,null,contentValues);
            }
        }finally {
            db.close();
        }
    }


    public void inserirSeries(List<Serie> series) {
        SQLiteDatabase db = getWritableDatabase();
        try{
            for (int i = 0; i < series.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(SerieContrato.SerieEntry.nomeserie, series.get(i).getName());
                cv.put(SerieContrato.SerieEntry.overview, series.get(i).getOverview());
                cv.put(SerieContrato.SerieEntry.original_name, series.get(i).getOriginal_name());
                cv.put(SerieContrato.SerieEntry.first_air_date, series.get(i).getFirst_air_date());
                cv.put(SerieContrato.SerieEntry.vote_average, series.get(i).getVote_average());
                cv.put(SerieContrato.SerieEntry.id_serie, series.get(i).getId());
                cv.put(SerieContrato.SerieEntry.poster_path,series.get(i).getPoster_path());
                db.insert(SerieContrato.SerieEntry.tabela,null,cv);
            }
        }finally {
            db.close();
        }
    }





    public List<Serie> listarMaisVotadosSerie() {
        return null;
    }

    public void inserirMaisVotadosSerie(List<Serie> lista) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            for (int i = 0; i < lista.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(SerieContrato.SerieEntry.nomeserie, lista.get(i).getName());
                cv.put(SerieContrato.SerieEntry.overview, lista.get(i).getOverview());
                cv.put(SerieContrato.SerieEntry.original_name, lista.get(i).getOriginal_name());
                cv.put(SerieContrato.SerieEntry.first_air_date, lista.get(i).getFirst_air_date());
                cv.put(SerieContrato.SerieEntry.vote_average, lista.get(i).getVote_average());
                cv.put(SerieContrato.SerieEntry.id_serie, lista.get(i).getId());
                cv.put(SerieContrato.SerieEntry.poster_path,lista.get(i).getPoster_path());
                db.insert(SerieContrato.SerieEntry.tabela+"MaisVotados", null, cv);
                Log.i("inseriuSerie",lista.get(i).getId()+"");
            }
        }finally {
            db.close();
        }
    }
}
