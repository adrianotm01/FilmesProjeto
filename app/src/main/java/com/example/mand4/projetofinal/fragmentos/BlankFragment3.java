package com.example.mand4.projetofinal.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mand4.projetofinal.R;
import com.example.mand4.projetofinal.adaptador.ReciclerAdapter;
import com.example.mand4.projetofinal.modelo.Noticia;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mand4 on 26/11/2017.
 */

public class BlankFragment3 extends Fragment {
    private List<Noticia> noticiaList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cartao,container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.reciclador);
        noticiaList.add(new Noticia("dsadsa","dsadsa",2,"dsadsad",2.5,"dsadsa",1));
        ReciclerAdapter adapter = new ReciclerAdapter(getContext(),noticiaList);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        return view;
    }
}
