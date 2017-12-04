package com.example.mand4.projetofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mand4.projetofinal.adaptador.AdapterPaginas;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


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
        return true;
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
