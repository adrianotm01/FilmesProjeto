package com.example.mand4.projetofinal.adaptador;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mand4.projetofinal.R;

/**
 * Created by mand4 on 21/11/2017.
 */

public class NoiticiaHolder extends RecyclerView.ViewHolder{
    private TextView titulo;
    private TextView ano;
    private TextView nota;
    private TextView categoria;
    private TextView mais;
    public NoiticiaHolder(View itemView) {
        super(itemView);
        titulo = (TextView) itemView.findViewById(R.id.titulo);
        ano = (TextView) itemView.findViewById(R.id.ano1);
        nota = (TextView) itemView.findViewById(R.id.nota);
        categoria = (TextView) itemView.findViewById(R.id.categorias);
    }

    public TextView getTitulo() {
        return titulo;
    }

    public void setTitulo(TextView titulo) {
        this.titulo = titulo;
    }

    public TextView getAno() {
        return ano;
    }

    public void setAno(TextView ano) {
        this.ano = ano;
    }

    public TextView getNota() {
        return nota;
    }

    public void setNota(TextView nota) {
        this.nota = nota;
    }

    public TextView getCategoria() {
        return categoria;
    }

    public void setCategoria(TextView categoria) {
        this.categoria = categoria;
    }

    public TextView getMais() {
        return mais;
    }

    public void setMais(TextView mais) {
        this.mais = mais;
    }
}
