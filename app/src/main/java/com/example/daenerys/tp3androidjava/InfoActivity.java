package com.example.daenerys.tp3androidjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class InfoActivity extends AppCompatActivity {

    User usuario;
    TextView nome, senha, email, telefone, celular, cidade, cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();

        Bundle b = intent.getExtras();
        usuario = (User) b.getSerializable("usuario");

        nome =  findViewById(R.id.textView3);
        senha =  findViewById(R.id.textSenha);
        email =  findViewById(R.id.textEmail);
        telefone =  findViewById(R.id.text);
        celular =  findViewById(R.id.textCel);
        cidade =  findViewById(R.id.textCid);
        cpf =  findViewById(R.id.textCpf);

        nome.setText(usuario.nome);
        senha.setText(usuario.senha);
        email.setText(usuario.email);
        telefone.setText(usuario.telefone);
        celular.setText(usuario.celular);
        cidade.setText(usuario.cidade);
        cpf.setText(usuario.cpf);

    }//onCreate
}//class
