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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.example.mand4.projetofinal.Main5Activity;
import com.example.mand4.projetofinal.Main6Activity;
import com.example.mand4.projetofinal.R;
import com.example.mand4.projetofinal.adaptador.ReciclerAdapter;
import com.example.mand4.projetofinal.banco.BancoHelper;
import com.example.mand4.projetofinal.listener.OnItemClickListener;
import com.example.mand4.projetofinal.listener.RecicladorListener;
import com.example.mand4.projetofinal.modelo.CatalogoVideos;
import com.example.mand4.projetofinal.modelo.Catalogos;
import com.example.mand4.projetofinal.modelo.Genero;
import com.example.mand4.projetofinal.modelo.Noticia;
import com.example.mand4.projetofinal.servico.ListaNoticiasService;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bumptech.glide.Glide.with;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    TextView texto;

    private List<Noticia> noticias;
    private RecyclerView reciclador;
    private ReciclerAdapter adapter;
    private List<Genero> generos = new ArrayList<>();
    FirebaseAuth auth;
    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.inflarfragmento,container,false);
        final BancoHelper b = new BancoHelper(getContext());
        auth = FirebaseAuth.getInstance();
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        reciclador.setLayoutManager(manager);
        reciclador.setNestedScrollingEnabled(false);
        if (b.listar().size() >  0)
            noticias = b.listar();


        final Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ListaNoticiasService service = retrofit2.create(ListaNoticiasService.class);
        Call<Catalogos> listCall = service.getCatalogo();

        if (noticias == null) {
            listCall.enqueue(new Callback<Catalogos>() {
                @Override
                public void onResponse(Call<Catalogos> call, Response<Catalogos> response) {
                    noticias = response.body().getResults();
                    adapter = new ReciclerAdapter(getContext(), noticias,generos);
                    reciclador.setAdapter(adapter);
                    b.inserirFilmes(noticias);
                    reciclador.addOnItemTouchListener(new RecicladorListener(getContext(), reciclador, new OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int posicao) {
                            final Bundle bundle = new Bundle();
                            bundle.putString("titulo",noticias.get(posicao).getTitle());
                            bundle.putString("original",noticias.get(posicao).getOriginal_title());
                            bundle.putString("descricao",noticias.get(posicao).getOverview());
                            bundle.putString("caminho",noticias.get(posicao).getBackdrop_path());
                            bundle.putDouble("nota",noticias.get(posicao).getVote_average());
                            bundle.putInt("orcamento",noticias.get(posicao).getRevenue());
                            bundle.putInt("tempo",noticias.get(posicao).getRuntime());
                            bundle.putInt("id",noticias.get(posicao).getId_filme());
                            ListaNoticiasService servico = retrofit2.create(ListaNoticiasService.class);
//                           Call<CatalogoVideos>videosCall =  servico.getVideos(noticias.get(posicao).getId_filme());
//                            videosCall.enqueue(new Callback<CatalogoVideos>() {
//                                @Override
//                                public void onResponse(Call<CatalogoVideos> call, Response<CatalogoVideos> response) {
//                                    if (response.body().getResults() != null) {
//                                        bundle.putString("chave",response.body().getResults().get(0).getKey());
//                                    }
//
//                                }
//
//                                @Override
//                                public void onFailure(Call<CatalogoVideos> call, Throwable t) {
//
//                                }
//                            });
                            Intent i = new Intent(getContext(), Main6Activity.class);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                    }));
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
                public void onFailure(Call<Catalogos> call, Throwable t) {
                    Log.i("falha", t.getMessage());
                }
            });
        }else {
            noticias = b.listar();
            adapter = new ReciclerAdapter(getContext(), noticias);
            reciclador.setAdapter(adapter);
            reciclador.addOnItemTouchListener(new RecicladorListener(getContext(), reciclador, new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int posicao) {
                    final Bundle bundle = new Bundle();
                    bundle.putString("titulo",noticias.get(posicao).getTitle());
                    bundle.putString("original",noticias.get(posicao).getOriginal_title());
                    bundle.putString("descricao",noticias.get(posicao).getOverview());
                    bundle.putString("caminho",noticias.get(posicao).getBackdrop_path());
                    bundle.putDouble("nota",noticias.get(posicao).getVote_average());

                    bundle.putInt("orcamento",noticias.get(posicao).getRevenue());
                    bundle.putInt("tempo",noticias.get(posicao).getRuntime());
                    bundle.putInt("id",noticias.get(posicao).getId_filme());
                    Log.i("dsa",noticias.get(posicao).getId_filme()+"");
                    ListaNoticiasService servico = retrofit2.create(ListaNoticiasService.class);
//                           Call<CatalogoVideos>videosCall =  servico.getVideos(noticias.get(posicao).getId_filme());
//                            videosCall.enqueue(new Callback<CatalogoVideos>() {
//                                @Override
//                                public void onResponse(Call<CatalogoVideos> call, Response<CatalogoVideos> response) {
//                                    if (response.body().getResults() != null) {
//                                        bundle.putString("chave",response.body().getResults().get(0).getKey());
//                                    }
//
//                                }
//
//                                @Override
//                                public void onFailure(Call<CatalogoVideos> call, Throwable t) {
//
//                                }
//                            });
                    Intent i = new Intent(getContext(), Main6Activity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }));
          //  TextView texxo = (TextView) getActivity().findViewById(R.id.categorias);
//            for (int i = 0; i <  noticias.size(); i++) {
//                for (int j = 0; j < noticias.get(i).getGenre_ids().size(); j++) {
//                    for (int k = 0; k < generos.size() ; k++) {
//                        if (noticias.get(i).getGenre_ids().get(j) == generos.get(k).getId()) {
//                            Log.i(noticias.get(i).getTitle(),generos.get(i).getName());
//                        }
//                    }
//                }
//
//            }
        }



        return view;
    }
    private void gerarGenero(){

        generos.add(new Genero("Ação",28));
        generos.add(new Genero("Aventura",12));
        generos.add(new Genero("Animação",16));
        generos.add(new Genero("Comédia", 35));
        generos.add(new Genero("Crime",80));
        generos.add(new Genero("Documentário",99));
        generos.add(new Genero("Drama",18));
        generos.add(new Genero("Família",10751));
        generos.add(new Genero("Fantasia",14));
        generos.add(new Genero("História", 36));
        generos.add(new Genero("Terror",27));
        generos.add(new Genero("Mistério",9648));
        generos.add(new Genero("Romance",10749));
        generos.add(new Genero("Cinema TV",10770));
        generos.add(new Genero("Thriller",53));
        generos.add(new Genero("Guerra",10752));
        generos.add(new Genero("Faroeste",37));

    }

}
