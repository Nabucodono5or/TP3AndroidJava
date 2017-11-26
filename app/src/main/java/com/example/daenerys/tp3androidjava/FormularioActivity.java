package com.example.daenerys.tp3androidjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FormularioActivity extends AppCompatActivity {
    EditText nome, senha, email, telefone, celular, cidade, cpf;
    Button btnLimpar, btnSalvar;
    ArrayList<EditText> entrada;
    private DatabaseReference mDatabase;
    String userId;
    private static final String TAG = "MyFirstFireBase";
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);


        mDatabase = firebaseDatabase.getReference();

        nome = findViewById(R.id.editTextNome);
        senha = findViewById(R.id.editTextSenha);
        email = findViewById(R.id.editTextEmail);
        telefone = findViewById(R.id.editTextTelefone);
        celular = findViewById(R.id.editTextCelular);
        cidade = findViewById(R.id.editTextCidade);
        cpf = findViewById(R.id.editTextCpf);

        btnLimpar = findViewById(R.id.btn_limpar);
        btnSalvar = findViewById(R.id.btn_salvar);


        cpf.addTextChangedListener(MaskUtil.insert(cpf, MaskType.CPF));
        telefone.addTextChangedListener(MaskUtil.insert(telefone, MaskType.TEL));
        celular.addTextChangedListener(MaskUtil.insert(celular, MaskType.CEL));

        entrada = new ArrayList<>();
        entrada.add(nome);
        entrada.add(senha);
        entrada.add(email);
        entrada.add(telefone);
        entrada.add(celular);
        entrada.add(cidade);
        entrada.add(cpf);

    }//onCreate


    private boolean isNull(){
        for(EditText e : entrada){
            if(e.getText().toString().matches("")){
                return true;
            }//if
        }//for e

        return false;
    }//isNull


    public void onClickLimpar(View view){
        limparText();
    }//onClickLimpar


    private void limparText(){
        for (EditText e: entrada) {
            e.setText("");
        }
    }


    public void onClickUsuarios(View view){
        Intent intent = new Intent(this, UsuariosActivity.class);
        startActivity(intent);
    }


    public void onClickSalvar(View view){
        if(isNull()){
            Toast.makeText(this, "Error: Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }else{

            if(TextUtils.isEmpty(userId)){
                createUser();
            } else {
                Toast.makeText(this, "usuario já cadastrado", Toast.LENGTH_LONG).show();
            }
        }//else
    }//onClickSalvar


    private void createUser(){

        if (TextUtils.isEmpty(userId)) {
            userId = mDatabase.push().getKey();
        }

        User usuario = new User(nome.getText().toString(), senha.getText().toString(),
                email.getText().toString(), telefone.getText().toString(),
                celular.getText().toString(), cidade.getText().toString(), cpf.getText().toString());

        mDatabase.child("users").child(userId).setValue(usuario);

        addUserChangeListener();
    }//createUser


    private void addUserChangeListener() {
        mDatabase.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.nome + ", " + user.email);

                limparText();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Failed to read user", databaseError.toException());
            }
        });
    }//addUserChangeListener
}//class
