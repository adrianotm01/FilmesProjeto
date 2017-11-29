package com.example.mand4.projetofinal.banco;

import android.provider.BaseColumns;

/**
 * Created by mand4 on 23/11/2017.
 */

public class NoticiaContrato {
    private NoticiaContrato(){

    }

    public static class NoticiaEntry implements BaseColumns{
        public static final String tabela = "filme";
        public static final String title = "titulo";
        public static final String overview = "descricao";
        public static final String imagem = "imagem";
        public static final String original_title = "titulo_original";
        public static final String data = "data_lancamento";
        public static final String nota = "nota";
        public static final String idFilme = "id_filme";
        public static final String runtime = "tempo_duracao";
        public static final String poster = "caminho_poster";
    }

}
