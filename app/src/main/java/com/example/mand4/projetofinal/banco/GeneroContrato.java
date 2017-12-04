package com.example.mand4.projetofinal.banco;

import android.provider.BaseColumns;

/**
 * Created by mand4 on 01/12/2017.
 */

public class GeneroContrato {
    private GeneroContrato(){

    }

    public static class GeneroEntry implements BaseColumns{
        public static final String tabela = "genero";
        public static final String nome = "nome";
        public static final String id_genero = "id";
    }
}
