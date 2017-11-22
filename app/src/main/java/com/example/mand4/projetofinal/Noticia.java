package com.example.mand4.projetofinal;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mand4 on 21/11/2017.
 */

public class Noticia {

    private String nome;
    private String descricao;
    private int imagem;
    private int visitas;
    private Date horas;

    public Noticia(String nome, String descricao, int imagem, int visitas, Date horas) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.visitas = visitas;
        this.horas = horas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

    public Date getHoras() {
        return horas;
    }

    public void setHoras(Date horas) {
        this.horas = horas;
    }
}
