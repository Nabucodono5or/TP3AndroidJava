package com.example.daenerys.tp3androidjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsuariosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<String> nomesUsuarios;
    private ArrayList<User> usuarios;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);


        nomesUsuarios = new ArrayList<>();
        usuarios = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, nomesUsuarios);
        ListView listView = findViewById(R.id.listViewContatos);
        listView.setAdapter(arrayAdapter);


        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                if(user == null){
                    menssagem("nenhum registro foi encontrado");
                }

                nomesUsuarios.add(user.nome);
                usuarios.add(user);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(this);

    }//onCreate


    public void menssagem(String sr){
        Toast.makeText(this,sr,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("usuario", usuarios.get(position));
        startActivity(intent);
    }
}//class
