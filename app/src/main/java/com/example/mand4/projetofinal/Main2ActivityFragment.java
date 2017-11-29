package com.example.mand4.projetofinal;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mand4.projetofinal.adaptador.ReciclerAdapter;
import com.example.mand4.projetofinal.banco.BancoHelper;
import com.example.mand4.projetofinal.modelo.Catalogos;
import com.example.mand4.projetofinal.modelo.Noticia;
import com.example.mand4.projetofinal.servico.AvaliadosService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class Main2ActivityFragment extends Fragment {
    private RecyclerView recyclerView;
    private ReciclerAdapter adaptador;
    private List<Noticia> lista;


    public Main2ActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final BancoHelper b = new BancoHelper(getContext());
        if(b.listarMaisVotados().size() > 0){
            lista = b.listarMaisVotados();
        }
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        recyclerView =  (RecyclerView) view.findViewById(R.id.reciclador2);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AvaliadosService service = retrofit.create(AvaliadosService.class);
        Call<Catalogos> call =  service.noticias();
        if(lista == null) {
            call.enqueue(new Callback<Catalogos>() {
                @Override
                public void onResponse(Call<Catalogos> call, Response<Catalogos> response) {
                    Log.i("pq nfoi",response.body().getResults().get(0).getPosterPath());
                    lista = response.body().getResults();
                    adaptador = new ReciclerAdapter(getContext(),lista);
                    recyclerView.setAdapter(adaptador);
                    b.inserirFilmes(lista);
                }


                @Override
                public void onFailure(Call<Catalogos> call, Throwable t) {

                }
            });
        }
        else {
            adaptador = new ReciclerAdapter(getContext(), lista);
            lista = b.listarMaisVotados();
        }
        recyclerView.setAdapter(adaptador);
        return view;
    }
}
