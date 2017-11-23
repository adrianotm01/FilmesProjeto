package com.example.mand4.projetofinal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
                return new BlankFragment();
            case 2:
                return new BlankFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Filmes";
            case 1:
                return "Series";
            case 2:
                return "Pessoas";
            default:
                return null;
        }
    }
}
