package com.example.mand4.projetofinal.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.mand4.projetofinal.R;
import com.example.mand4.projetofinal.modelo.Genero;
import com.example.mand4.projetofinal.modelo.Serie;

import java.util.List;

/**
 * Created by mand4 on 30/11/2017.
 */

public class AdaptadorSerie extends RecyclerView.Adapter{

    private Context contexto;
    private List<Serie> series;
    private List<Genero> generos;
    public AdaptadorSerie(Context contexto, List<Serie> series){
        this.contexto = contexto;
        this.series = series;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.cartao,parent,false);
        NoiticiaHolder holder = new NoiticiaHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NoiticiaHolder hoslder = (NoiticiaHolder) holder;
        hoslder.getTitulo().setText(series.get(position).getName());
        hoslder.getAno().setText(series.get(position).getFirst_air_date().substring(0,4));
        hoslder.getNota().setText(String.valueOf(series.get(position).getVote_average()));
        Glide.with(contexto)
                .load("https://image.tmdb.org/t/p/w300/"+series.get(position).getPoster_path())
                .into(hoslder.getImagem());
//        for (int i = 0; i <  series.size(); i++) {
//            for (int j = 0; j < series.get(i).getGenre_ids().size(); j++) {
//                for (int k = 0; k < generos.size() ; k++) {
//                    if (series.get(i).getGenre_ids().get(j) == generos.get(k).getId()) {
//                    }
//                }
//            }
//
//        }
    }

    @Override
    public int getItemCount() {
        return series.size();
    }
}
