package com.example.mand4.projetofinal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.example.mand4.projetofinal.adaptador.ReciclerAdapter;
import com.example.mand4.projetofinal.banco.BancoHelper;
import com.example.mand4.projetofinal.modelo.Catalogos;
import com.example.mand4.projetofinal.modelo.Noticia;
import com.example.mand4.projetofinal.servico.ListaNoticiasService;

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

    public BlankFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.inflarfragmento,container,false);
        final BancoHelper b = new BancoHelper(getContext());
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        reciclador.setLayoutManager(manager);
        if (b.listar().size() >  0) {
            Log.i("aqui","iu");
            noticias = b.listar();
        }
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.themoviedb.org/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        NoticiaService servico = retrofit.create(NoticiaService.class);
//        Call<Noticia> call = servico.getNoticia(540);
//        call.enqueue(new Callback<Noticia>() {
//            @Override
//            public void onResponse(Call<Noticia> call, Response<Noticia> response) {
//                texto.setText(response.body().getTitle());
//            }
//
//            @Override;
//            public void onFailure(Call<Noticia> call, Throwable t) {
//                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
//            }
//        });

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ListaNoticiasService service = retrofit2.create(ListaNoticiasService.class);
        Call<Catalogos> listCall = service.getCatalogo();
        if (noticias == null) {
            Log.i("e","if");
            listCall.enqueue(new Callback<Catalogos>() {
                @Override
                public void onResponse(Call<Catalogos> call, Response<Catalogos> response) {
                    noticias = response.body().getResults();
                    adapter = new ReciclerAdapter(getContext(), noticias);
                    reciclador.setAdapter(adapter);
                    b.inserirFilmes(noticias);
                    BitmapTypeRequest<String> bitmap = Glide
                            .with(getContext())
                            .load("https://image.tmdb.org/t/p/w500/"+noticias.get(0).getPosterPath())
                            .asBitmap();
                }

                @Override
                public void onFailure(Call<Catalogos> call, Throwable t) {
                    Log.i("falha", t.getMessage());
                }
            });
        }else {
            Log.i("e","n entendo");
            noticias = b.listar();
            adapter = new ReciclerAdapter(getContext(), noticias);
            reciclador.setAdapter(adapter);
        }

//        if(noticias != null) {
//            Log.i("e","n entendo");
//            for (int i = 0; i < noticias.size(); i++) {
//                b.inserir(noticias.get(i));
//                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.reciclador);
//                recyclerView.setAdapter(new ReciclerAdapter(getContext(),noticias));
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//                recyclerView.setLayoutManager(layoutManager);
//            }
//        }
//        else
//            Toast.makeText(getContext(), "N FOI", Toast.LENGTH_SHORT).show();

        return view;
    }

}
