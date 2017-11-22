package com.example.mand4.projetofinal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by mand4 on 21/11/2017.
 */

public class ReciclerAdapter extends RecyclerView.Adapter{
    Context contexto;

    public ReciclerAdapter(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
