package com.example.monitorar;

import static android.os.SystemClock.sleep;

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
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class Cadastro extends AppCompatActivity {

    private TextView voltar;
    private Button btnCadastrar;
    private EditText txtNome, txtEmail, txtSenha, txtCEP;
    String erro = "Todos os campos devem ser preenchidos.";
    String tamanho_CEP = "O CEP deve ter 8 dígitos.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

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
                } else if(cep.length()<8){
                    Snackbar snackbar = Snackbar.make(view, tamanho_CEP, Snackbar.LENGTH_SHORT);
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
                    Intent intent = new Intent(Cadastro.this,Login.class);
                    startActivity(intent);

                } else{
                    String mensagem;
                    try {
                        throw task.getException();

                    }catch(FirebaseAuthWeakPasswordException e){
                        mensagem = "A senha deve ter o mínimo de 6 caracteres.";
                    }catch(FirebaseAuthUserCollisionException e){
                        mensagem = "Este usuário já está cadastrado.";
                    } catch(FirebaseAuthInvalidCredentialsException e){
                        mensagem = "E-mail inválido.";
                    } catch (Exception e){
                        mensagem = "Ocorreu um erro inesperado durante o cadastro.";
                    }
                    Snackbar snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT);
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