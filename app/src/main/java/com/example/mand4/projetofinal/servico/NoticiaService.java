package com.example.mand4.projetofinal.servico;

import com.example.mand4.projetofinal.R;
import com.example.mand4.projetofinal.modelo.Noticia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mand4 on 22/11/2017.
 */

public interface NoticiaService {
    @GET("/3/movie/{movie_id}?api_key="+ R.string.api_key)

    Call<Noticia> getNoticia(@Path("movie_id") int id);
}
