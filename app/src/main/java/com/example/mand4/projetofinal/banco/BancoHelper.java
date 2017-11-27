package com.example.mand4.projetofinal.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mand4.projetofinal.modelo.Noticia;

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
            texto+","+ NoticiaContrato.NoticiaEntry.runtime+texto+", "+NoticiaContrato.NoticiaEntry.original_title+texto+", "
            + NoticiaContrato.NoticiaEntry.data+" "+texto+", "+ NoticiaContrato.NoticiaEntry.nota+" "+real+", "+
            NoticiaContrato.NoticiaEntry.idFilme+" INTEGER);");
    public BancoHelper(Context context){
        super(context,"projeto_final",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(criar_Tabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void inserir(Noticia noticia){
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NoticiaContrato.NoticiaEntry.title, noticia.getTitle());
            contentValues.put(NoticiaContrato.NoticiaEntry.overview, noticia.getOverview());
            contentValues.put(NoticiaContrato.NoticiaEntry.original_title, noticia.getOriginal_title());
            contentValues.put(NoticiaContrato.NoticiaEntry.data, noticia.getRelease_date());
            contentValues.put(NoticiaContrato.NoticiaEntry.nota, noticia.getVote_average());
            contentValues.put(NoticiaContrato.NoticiaEntry.idFilme, noticia.getId_filme());
            db.insert(NoticiaContrato.NoticiaEntry.tabela, null, contentValues);
        }finally {
            db.close();
        }
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

    private List<Noticia> findAll(Cursor c) {
        SQLiteDatabase db = getReadableDatabase();
        List<Noticia> noticias = new ArrayList<>();
        try {
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
                    noticias.add(not);
                } while (c.moveToNext());
            }
            return noticias;
        }finally {
            db.close();
        }
    }

    public void inserir(List<Noticia> list){
        SQLiteDatabase db = getWritableDatabase();
        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(NoticiaContrato.NoticiaEntry.title, list.get(i).getTitle());
                cv.put(NoticiaContrato.NoticiaEntry.overview, list.get(i).getOverview());
                cv.put(NoticiaContrato.NoticiaEntry.original_title, list.get(i).getOriginal_title());
                cv.put(NoticiaContrato.NoticiaEntry.data, list.get(i).getRelease_date());
                cv.put(NoticiaContrato.NoticiaEntry.nota, list.get(i).getVote_average());
                cv.put(NoticiaContrato.NoticiaEntry.idFilme, list.get(i).getId_filme());
                db.insert(NoticiaContrato.NoticiaEntry.tabela, null, cv);
                Log.i("testando","inseriu");
            }
        }finally {
            db.close();
        }
    }
}
