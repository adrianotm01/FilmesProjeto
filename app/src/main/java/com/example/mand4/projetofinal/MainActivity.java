package com.example.mand4.projetofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mand4.projetofinal.adaptador.AdapterPaginas;
import com.example.mand4.projetofinal.modelo.Catalogos;
import com.example.mand4.projetofinal.servico.ListaNoticiasService;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity{

    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private FirebaseAuth auth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(new AdapterPaginas(getSupportFragmentManager()));
        TabLayout tab = (TabLayout) findViewById(R.id.aba);
        tab.setupWithViewPager(pager);
        mStatusTextView = (TextView) findViewById(R.id.status);
        mDetailTextView = (TextView) findViewById(R.id.detail);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchFiltro());
        return true;
    }
    private class SearchFiltro implements SearchView.OnQueryTextListener{

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    Retrofit retrofi = new Retrofit.Builder()
                            .baseUrl("https://api.themoviedb.org/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ListaNoticiasService service = retrofi.create(ListaNoticiasService.class);
                    Call<Catalogos> catalogosCall = service.getProcura(query);
                    catalogosCall.enqueue(new Callback<Catalogos>() {
                        @Override
                        public void onResponse(Call<Catalogos> call, Response<Catalogos> response) {
                            Log.i("procura",response.body().getResults().get(0).getTitle());
                        }

                        @Override
                        public void onFailure(Call<Catalogos> call, Throwable t) {

                        }
                    });
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("trocou", "mudou");
                return false;
            }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.logout){
            deslogar();
        }


        return super.onOptionsItemSelected(item);
    }
    public void maisAvaliados(View view){
        Intent i = new Intent(this,Main2Activity.class);
        startActivity(i);
    }

    public void maisAvaliadosSeries(View v){
        Intent i = new Intent(this,Main3Activity.class);
        startActivity(i);
    }

    public void emCartaz(View view){
        Intent i = new Intent(this,Main4Activity.class);
        startActivity(i);
    }

    public void popular(View view){
        Toast.makeText(this, "Populares", Toast.LENGTH_SHORT).show();
    }

    public void deslogar(){
        auth.signOut();
        Toast.makeText(this, "Deslogou", Toast.LENGTH_SHORT).show();
        finish();
    }



}
