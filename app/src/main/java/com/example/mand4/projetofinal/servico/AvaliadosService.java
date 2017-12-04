package com.example.mand4.projetofinal.servico;

import com.example.mand4.projetofinal.R;
import com.example.mand4.projetofinal.modelo.CatalogoSerie;
import com.example.mand4.projetofinal.modelo.Catalogos;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mand4 on 28/11/2017.
 */

public interface AvaliadosService {
    @GET("movie/top_rated?api_key=5e67360fafc93e4e61616f3678883c96&language=pt-BR&page=1")
    Call<Catalogos> noticias();

    @GET("tv/top_rated?api_key=5e67360fafc93e4e61616f3678883c96&language=pt-BR&page=1")
    Call<CatalogoSerie> series();

    @GET("movie/now_playing?api_key=5e67360fafc93e4e61616f3678883c96&language=pt-BR&page=1")
    Call<Catalogos> getEmCartaz();

}
