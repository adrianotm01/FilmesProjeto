package com.example.mand4.projetofinal.banco;

import android.provider.BaseColumns;

/**
 * Created by mand4 on 02/12/2017.
 */

public class SerieContrato {
    private SerieContrato(){

    }

    public static class SerieEntry implements BaseColumns{
        public static final String tabela = "series";
        public static final String nomeserie = "nome";
        public static  final String overview = "descricao";
        public static  final String original_name = "nome_original";
        public static  final String vote_average = "nota";
        public static  final String first_air_date = "primeiro_ep";
        public static  final String runtime = "tempo";
        public static  final String id_serie = "id_serie";
        public static  final String poster_path = "caminho";
        public static  final String last_air_date = "ultimo_Dia";
    }
}
