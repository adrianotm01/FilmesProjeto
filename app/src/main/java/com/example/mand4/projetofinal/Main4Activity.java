package com.example.mand4.projetofinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mand4.projetofinal.adaptador.ReciclerAdapter;
import com.example.mand4.projetofinal.modelo.CatalogoSerie;
import com.example.mand4.projetofinal.modelo.Catalogos;
import com.example.mand4.projetofinal.modelo.Noticia;
import com.example.mand4.projetofinal.servico.AvaliadosService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main4Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Noticia> noticias;
    private  ReciclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.reciclador4);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AvaliadosService service = retrofit.create(AvaliadosService.class);
        Call<Catalogos> call =  service.getEmCartaz();
        call.enqueue(new Callback<Catalogos>() {
            @Override
            public void onResponse(Call<Catalogos> call, Response<Catalogos> response) {
             adapter  = new ReciclerAdapter(getBaseContext(),response.body().getResults());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Catalogos> call, Throwable t) {

            }
        });
    }

}
