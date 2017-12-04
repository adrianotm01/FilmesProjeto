package com.example.mand4.projetofinal.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mand4 on 03/12/2017.
 */

public class RecicladorListener implements RecyclerView.OnItemTouchListener{
    private OnItemClickListener listener;
    GestureDetector detector;
    public RecicladorListener(Context context, final RecyclerView reciclador, final OnItemClickListener listener) {
        this.listener = listener;
        detector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                super.onSingleTapUp(e);
                View crianca = reciclador.findChildViewUnder(e.getX(),e.getY());
                if (crianca != null && listener != null) {
                    listener.onItemClick(crianca,reciclador.getChildAdapterPosition(crianca));
                    Log.i("tocou","click");
                }
                return true ;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e){
        detector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
