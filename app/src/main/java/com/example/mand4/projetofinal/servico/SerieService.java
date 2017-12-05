package com.example.mand4.projetofinal.servico;

import com.example.mand4.projetofinal.R;
import com.example.mand4.projetofinal.modelo.CatalogoSerie;
import com.example.mand4.projetofinal.modelo.CatalogoVideos;
import com.example.mand4.projetofinal.modelo.Catalogos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by mand4 on 26/11/2017.
 */

public interface SerieService {
    @GET("3/tv/popular?api_key=5e67360fafc93e4e61616f3678883c96&language=pt-BR")
    Call<CatalogoSerie> getSeries();

    @GET("3/tv/{tv_id}/videos?api_key=5e67360fafc93e4e61616f3678883c96&language=en-US")
    Call<CatalogoVideos> getVideos(@Path("tv_id") int tv_id);
}
