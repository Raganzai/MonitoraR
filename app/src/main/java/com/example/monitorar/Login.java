package com.example.monitorar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextView cadastrar;
    private Button btnEntrar;
    private EditText txtEmail, txtSenha;
    private ProgressBar ProgressBar;
    String erro = "Todos os campos devem ser preenchidos.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Inicializar();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Cadastro.class);
                startActivity(intent);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String senha = txtSenha.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                } else{
                    Verificar(view);
                }
            }
        });

    }

    private void Verificar(View view){
        ProgressBar.setVisibility(View.VISIBLE);
        String email = txtEmail.getText().toString();
        String senha = txtSenha.getText().toString();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);

                } else{
                    String mensagem = "Verifique se os dados cadastrados estão corretos.";
                    Snackbar snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                    ProgressBar.setVisibility(View.INVISIBLE);
                    txtEmail.setText("");
                    txtSenha.setText("");
        }}});}

    private void Inicializar(){
        cadastrar = findViewById(R.id.cadastrar);
        btnEntrar = findViewById(R.id.btnEntrar);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        ProgressBar = findViewById(R.id.progress_bar);
    }


}