package com.example.mand4.projetofinal.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.mand4.projetofinal.R;
import com.example.mand4.projetofinal.modelo.Genero;
import com.example.mand4.projetofinal.modelo.Noticia;

import java.util.List;

/**
 * Created by mand4 on 21/11/2017.
 */

public class ReciclerAdapter extends RecyclerView.Adapter{
    private List<Genero> generos;
    Context contexto;
    private List<Noticia> noticiaList;
    public ReciclerAdapter(Context contexto, List<Noticia> list) {
        this.contexto = contexto;
        noticiaList = list;
    }

    public ReciclerAdapter(Context context, List<Noticia> noticias, List<Genero> generos) {
        this.contexto = context;
        this.noticiaList = noticias;
        this.generos = generos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto).inflate(R.layout.cartao,parent,false);
        NoiticiaHolder noiticiaHolder = new NoiticiaHolder(view);
        return noiticiaHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NoiticiaHolder hoslder = (NoiticiaHolder) holder;
        hoslder.getTitulo().setText(noticiaList.get(position).getTitle());
        hoslder.getAno().setText(noticiaList.get(position).getRelease_date().substring(0,4));
        hoslder.getNota().setText(""+noticiaList.get(position).getVote_average());
        Glide.with(contexto)
                .load("https://image.tmdb.org/t/p/w300/"+noticiaList.get(position).getPosterPath()).
                into(hoslder.getImagem());
//        for (int i = 0; i <  noticiaList.size(); i++) {
//            String concater = "";
//            for (int j = 0; j < noticiaList.get(i).getGenre_ids().size(); j++) {
//                for (int k = 0; k < generos.size() ; k++) {
//                    if (noticiaList.get(i).getGenre_ids().get(j) == generos.get(k).getId()) {
//                        Log.i(noticiaList.get(i).getTitle(),generos.get(k).getName());
//                    }
//                }
//            }
//
//        }
        if (position == noticiaList.size()-1){
            
        }
    }

    @Override
    public int getItemCount() {
        return noticiaList.size();
    }
}
