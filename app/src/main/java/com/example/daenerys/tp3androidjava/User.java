package com.example.daenerys.tp3androidjava;

import java.io.Serializable;

/**
 * Created by daenerys on 11/24/17.
 */

public class User  implements Serializable{

    String nome;
    String senha;
    String email;
    String telefone;
    String celular;
    String cidade;
    String cpf;


    public User() {
    }//constructor


    public User(String nome, String senha, String email,
                String telefone, String celular, String cidade,
                String cpf) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.cidade = cidade;
        this.cpf = cpf;
    }//constructor
}//class
