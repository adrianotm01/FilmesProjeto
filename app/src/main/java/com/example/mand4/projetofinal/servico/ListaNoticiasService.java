package com.example.mand4.projetofinal.servico;

import com.example.mand4.projetofinal.R;
import com.example.mand4.projetofinal.modelo.CatalogoVideos;
import com.example.mand4.projetofinal.modelo.Catalogos;
import com.example.mand4.projetofinal.modelo.Noticia;
import com.example.mand4.projetofinal.modelo.Videos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mand4 on 22/11/2017.
 */

public interface ListaNoticiasService {
    @GET("3/movie/popular?api_key=5e67360fafc93e4e61616f3678883c96&language=pt-BR&page=1")
    Call<Catalogos> getCatalogo();

    @GET("3/movie/{movie_id}/videos?api_key=5e67360fafc93e4e61616f3678883c96&language=en-US")
    Call<CatalogoVideos> getVideos(@Path("movie_id") int movie_id);

    @GET("3/search/movie?api_key=5e67360fafc93e4e61616f3678883c96&language=pt-BR&query={query}")
    Call<Catalogos> getProcura(@Path("query") String query);
}
