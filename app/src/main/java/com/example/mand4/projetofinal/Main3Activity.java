package com.example.mand4.projetofinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mand4.projetofinal.adaptador.AdaptadorSerie;
import com.example.mand4.projetofinal.adaptador.ReciclerAdapter;
import com.example.mand4.projetofinal.banco.BancoHelper;
import com.example.mand4.projetofinal.modelo.CatalogoSerie;
import com.example.mand4.projetofinal.modelo.Catalogos;
import com.example.mand4.projetofinal.modelo.Serie;
import com.example.mand4.projetofinal.servico.AvaliadosService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main3Activity extends AppCompatActivity {
    BancoHelper b = new BancoHelper(this);
    private List<Serie> lista;
    private AdaptadorSerie adaptador;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.reciclador3);

        if(b.listarMaisVotados().size() > 0){
            lista = b.listarMaisVotadosSerie();
        }
        RecyclerView.LayoutManager manage = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manage);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AvaliadosService service = retrofit.create(AvaliadosService.class);
        Call<CatalogoSerie> call =  service.series();
        if(lista == null) {
            call.enqueue(new Callback<CatalogoSerie>() {
                @Override
                public void onResponse(Call<CatalogoSerie> call, Response<CatalogoSerie> response) {
                    lista = response.body().getResults();
                    adaptador = new AdaptadorSerie(getBaseContext(), lista);
                    recyclerView.setAdapter(adaptador);
                    b.inserirMaisVotadosSerie(lista);
                }

                @Override
                public void onFailure(Call<CatalogoSerie> call, Throwable t) {

                }
            });
        }
        else {
            lista = b.listarMaisVotadosSerie();
            adaptador = new AdaptadorSerie(this, lista);
            recyclerView.setAdapter(adaptador);
        }
    }

}
