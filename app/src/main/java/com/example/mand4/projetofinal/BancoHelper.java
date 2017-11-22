package com.example.mand4.projetofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mand4 on 21/11/2017.
 */

public class BancoHelper extends SQLiteOpenHelper {
    private final String criar_Tabela = "CREATE TABLE ();";
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
}
