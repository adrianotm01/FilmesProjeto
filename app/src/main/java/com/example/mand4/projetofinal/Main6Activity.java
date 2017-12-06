package com.example.mand4.projetofinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mand4.projetofinal.modelo.CatalogoVideos;
import com.example.mand4.projetofinal.servico.ListaNoticiasService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main6Activity extends AppCompatActivity {
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bundle = getIntent().getExtras();
        TextView titulo = (TextView) findViewById(R.id.tituloAtividade);
        ImageView imamge = (ImageView) findViewById(R.id.imagemfilme);
        TextView orcamento = (TextView) findViewById(R.id.receita);
        TextView tempo = (TextView) findViewById(R.id.tempo);
        RatingBar rating = (RatingBar) findViewById(R.id.votos);
        TextView descri = (TextView) findViewById(R.id.descricion);
        if (bundle != null) {
            titulo.setText(bundle.getString("titulo"));
            getSupportActionBar().setTitle(bundle.getString("titulo"));
            rating.setRating((float) (bundle.getDouble("nota") / 2));
            orcamento.setText("R$ " + bundle.getInt("orcamento"));
            int novo = bundle.getInt("tempo");
            novo = novo / 60;
            int resto = novo / 60;
            tempo.setText(novo + ":" + resto + " horas");
            descri.setText(bundle.getString("descricao"));
            Glide.with(this).load("https://image.tmdb.org/t/p/w300/" + bundle.getString("caminho")).into(imamge);

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void verTrailer(View v) {


        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ListaNoticiasService service = retro.create(ListaNoticiasService.class);
        Log.i("teste",bundle.getInt("id")+"");
        Call<CatalogoVideos> videos = service.getVideos(bundle.getInt("id"));
        videos.enqueue(new Callback<CatalogoVideos>() {
            @Override
            public void onResponse(Call<CatalogoVideos> call, Response<CatalogoVideos> response) {
                if(!(response.body().getResults() == null)) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + response.body()
                            .getResults()
                            .get(0).getKey())));
                }
            }

            @Override
            public void onFailure(Call<CatalogoVideos> call, Throwable t) {

            }
        });

}

}
