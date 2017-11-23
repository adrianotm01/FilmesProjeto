package com.example.mand4.projetofinal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mand4 on 21/11/2017.
 */

public class ReciclerAdapter extends RecyclerView.Adapter{
    Context contexto;
    private List<Noticia> noticiaList;
    public ReciclerAdapter(Context contexto, List<Noticia> list) {
        this.contexto = contexto;
        noticiaList = list;
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
        hoslder.getTitulo().setText(noticiaList.get(position).getOriginal_title());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
