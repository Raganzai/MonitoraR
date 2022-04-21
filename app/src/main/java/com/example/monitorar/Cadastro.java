package com.example.monitorar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {

    private TextView voltar;
    private Button btnCadastrar;
    private EditText txtNome, txtEmail, txtSenha, txtCEP;
    String erro = "Todos os campos devem ser preenchidos.";
    String sucesso = "O cadastro foi efetuado.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();
        Inicializar();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cadastro.this,Login.class);
                startActivity(intent);
            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = txtNome.getText().toString();
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();
                String cep = txtCEP.getText().toString();

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cep.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else{
                    Cadastrar(view);
                }
            }
        });
    }

    private void Cadastrar(View view){

        String email = txtEmail.getText().toString();
        String senha = txtSenha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Snackbar snackbar = Snackbar.make(view, sucesso, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }
            }
        });
    }

    private void Inicializar(){
        voltar  = findViewById(R.id.Voltar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        txtCEP = findViewById(R.id.txtCEP);
    }
}