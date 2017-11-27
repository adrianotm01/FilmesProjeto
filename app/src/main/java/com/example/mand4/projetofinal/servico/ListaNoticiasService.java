package com.example.mand4.projetofinal.servico;

import com.example.mand4.projetofinal.modelo.Catalogos;
import com.example.mand4.projetofinal.modelo.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mand4 on 22/11/2017.
 */

public interface ListaNoticiasService {
    @GET("3/movie/popular?api_key=5e67360fafc93e4e61616f3678883c96&language=pt-BR&page=1")
    Call<Catalogos> getCatalogo();
}
