package com.example.mand4.projetofinal.fragmentos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mand4.projetofinal.R;
import com.example.mand4.projetofinal.adaptador.AdaptadorSerie;
import com.example.mand4.projetofinal.banco.BancoHelper;
import com.example.mand4.projetofinal.listener.OnItemClickListener;
import com.example.mand4.projetofinal.listener.RecicladorListener;
import com.example.mand4.projetofinal.modelo.CatalogoSerie;
import com.example.mand4.projetofinal.modelo.CatalogoVideos;
import com.example.mand4.projetofinal.modelo.Serie;
import com.example.mand4.projetofinal.servico.SerieService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BlankFragment2 extends Fragment {
    private List<Serie> series;
    private RecyclerView reciclador;
    private AdaptadorSerie adapter;

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inflarfragmento2, container, false);
        final BancoHelper b = new BancoHelper(getContext());
        reciclador = (RecyclerView) view.findViewById(R.id.recicladorfragmento3);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        reciclador.setLayoutManager(manager);
        reciclador.setNestedScrollingEnabled(false);
        if (b.listarSeries().size() > 0)
            series = b.listarSeries();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final SerieService service = retrofit2.create(SerieService.class);
        Call<CatalogoSerie> listCall = service.getSeries();
        if (series == null) {
            listCall.enqueue(new Callback<CatalogoSerie>() {
                @Override
                public void onResponse(Call<CatalogoSerie> call, Response<CatalogoSerie> response) {
                    series = response.body().getResults();
                    adapter = new AdaptadorSerie(getContext(), series);
                    reciclador.setAdapter(adapter);
                    reciclador.addOnItemTouchListener(new RecicladorListener(getContext(), reciclador, new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int posicao) {
                            Call<CatalogoVideos> videos = service.getVideos((int)series.get(posicao).getId());
                            videos.enqueue(new Callback<CatalogoVideos>() {
                                @Override
                                public void onResponse(Call<CatalogoVideos> call, Response<CatalogoVideos> response) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+response.body()
                                            .getResults()
                                            .get(0).getKey())));

                                }

                                @Override
                                public void onFailure(Call<CatalogoVideos> call, Throwable t) {
                                    Log.i("error",t.getMessage());
                                }
                            });
                        }
                    }));
                    b.inserirSeries(series);
                    Log.i("insere","inseriu");
//                    TextView texxo = (TextView) getActivity().findViewById(R.id.categorias);
//                    for (int i = 0; i <  noticias.size(); i++) {
//                        for (int j = 0; j < noticias.get(i).getGenre_ids().size(); j++) {
//                            for (int k = 0; k < generos.size() ; k++) {
//                                if (noticias.get(i).getGenre_ids().get(j) == generos.get(k).getId()) {
//                                    texxo.setText(generos.get(k).getName());
//                                }
//                            }
//                        }
//
//                    }
                }

                @Override
                public void onFailure(Call<CatalogoSerie> call, Throwable t) {
                    Log.i("falha", t.getMessage());
                }
            });

        } else {
            series = b.listarSeries();
            adapter = new AdaptadorSerie(getContext(),series);
            reciclador.setAdapter(adapter);
            reciclador.addOnItemTouchListener(new RecicladorListener(getContext(), reciclador, new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int posicao) {
                    Call<CatalogoVideos> videos = service.getVideos((int)series.get(posicao).getId());
                    videos.enqueue(new Callback<CatalogoVideos>() {
                        @Override
                        public void onResponse(Call<CatalogoVideos> call, Response<CatalogoVideos> response) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+response.body()
                                    .getResults()
                                    .get(0).getKey())));

                        }

                        @Override
                        public void onFailure(Call<CatalogoVideos> call, Throwable t) {
                            Log.i("error",t.getMessage());
                        }
                    });
                }
            }));
        }

        return view;
    }


}
