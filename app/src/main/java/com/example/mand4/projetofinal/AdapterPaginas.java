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
            case 3:
                return new BlankFragment();
            case 4:
                return new BlankFragment();
            case 5:
                return new BlankFragment();
            case 6:
                return new BlankFragment();
            case 7:
                return new BlankFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Games";
            case 1:
                return "HQS";
            case 2:
                return "Series";
            case 3:
                return "Youtube";
            case 4:
                return "Reviews";
            case 5:
                return "Listas";
            case 6:
                return "Equipe";
            case 7:
                return "Contato";
            default:
                return null;
        }
    }
}
