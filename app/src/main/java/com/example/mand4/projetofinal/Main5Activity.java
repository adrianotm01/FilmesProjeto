package com.example.mand4.projetofinal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main5Activity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText mEmailField;
    private EditText mPasswordField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mEmailField = (EditText) findViewById(R.id.field_email);
        mPasswordField = (EditText) findViewById(R.id.field_password);
        auth = FirebaseAuth.getInstance();

    }

    private void criarContaEmail(String email,String senha){
        if (validado()){
            return;
        }
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Main5Activity.this, "Criado com sucesso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void logarConta(String email,String senha){
        if (validado())
            return;
        auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser usuario = auth.getCurrentUser();

                }
            }
        });
    }
    public void deslogar(){
        auth.signOut();
        Toast.makeText(this, "Deslogou", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void logar(View view){
        logarConta(mEmailField.getText().toString(),mPasswordField.getText().toString());
    }
    private boolean validado() {
        String email = mEmailField.getText().toString();
        String senha = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)) {
            mEmailField.setError("Campo obrigatório");
            mPasswordField.setError("Campo obrigatório");
            return true;
        }
        return false;
    }
    public void criar(View view){
        criarContaEmail(mEmailField.getText().toString(),mPasswordField.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
