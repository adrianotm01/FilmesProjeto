package com.example.mand4.projetofinal.adaptador;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mand4.projetofinal.fragmentos.BlankFragment;
import com.example.mand4.projetofinal.fragmentos.BlankFragment2;

/**
 * Created by mand4 on 21/11/2017.
 */

public class AdapterPaginas extends FragmentPagerAdapter {

    public AdapterPaginas(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BlankFragment();
            case 1:
                return new BlankFragment2();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Filmes";
            case 1:
                return "Series";

            default:
                return null;
        }
    }
}
