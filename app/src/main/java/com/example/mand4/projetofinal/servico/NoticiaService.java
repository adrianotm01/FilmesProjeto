package com.example.mand4.projetofinal.servico;

import com.example.mand4.projetofinal.modelo.Noticia;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mand4 on 22/11/2017.
 */

public interface NoticiaService {
    @GET("/3/movie/{movie_id}?api_key=5e67360fafc93e4e61616f3678883c96")

    Call<Noticia> getNoticia(@Path("movie_id") int id);
}
