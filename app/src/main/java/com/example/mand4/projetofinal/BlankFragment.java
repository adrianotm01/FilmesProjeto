package com.example.mand4.projetofinal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mand4.projetofinal.servico.NoticiaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    TextView texto;

    public BlankFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inflarfragmento,container,false);
         texto = view.findViewById(R.id.textView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NoticiaService servico = retrofit.create(NoticiaService.class);
        Call<Noticia> call = servico.getNoticia(540);
        call.enqueue(new Callback<Noticia>() {
            @Override
            public void onResponse(Call<Noticia> call, Response<Noticia> response) {
                texto.setText(response.body().getOriginal_title());
            }

            @Override
            public void onFailure(Call<Noticia> call, Throwable t) {
                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        
        return view;
    }

}
