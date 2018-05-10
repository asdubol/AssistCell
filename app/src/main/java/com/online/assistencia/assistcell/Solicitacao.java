package com.online.assistencia.assistcell;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Solicitacao extends AppCompatActivity {
    EditText editMarca;
    EditText editModelo;
    EditText editProduto;
    EditText editQuant;
    EditText editNome;
    EditText editEmail;
    EditText editTelefone;
    Button botEnviar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacao);

        editMarca = (EditText) findViewById(R.id.editMarca);
        editModelo = (EditText) findViewById(R.id.editModelo);
        editProduto = (EditText) findViewById(R.id.editProduto);
        editQuant = (EditText) findViewById(R.id.editQuant);
        editNome = (EditText) findViewById(R.id.editNome);
        editTelefone = (EditText) findViewById(R.id.editTelefone);
        editEmail = (EditText) findViewById(R.id.editEmail);
        botEnviar = (Button) findViewById(R.id.botEnviar);


        botEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editProduto.getText().length() == 0 || (editNome.getText().length() == 0) || (editTelefone.getText().length() == 0)
                        || editQuant.getText().length() == 0 || editMarca.getText().length() == 0 || editModelo.getText().length() == 0) {
                    Toast.makeText(getApplication(), "Os campos 'Marca', 'Modelo', 'Descrição do produto', 'Quantidade', 'Nome' e 'Seu Telefone' são obrigatórios!",
                            Toast.LENGTH_LONG).show();
                    //não coloquei email como campo obrigatório porquê
                    // o cliente que deseja um determinado produto pode não ter email, mas com certeza terá um telefone
                } else {
                    NewSolicitacao newSolicitacao = new NewSolicitacao();
                    newSolicitacao.setId(UUID.randomUUID().toString());
                    newSolicitacao.setMarca(editMarca.getText().toString());
                    newSolicitacao.setModelo(editModelo.getText().toString());
                    newSolicitacao.setProduto(editProduto.getText().toString());
                    newSolicitacao.setQuant(editQuant.getText().toString());
                    newSolicitacao.setTelefone(editTelefone.getText().toString());
                    newSolicitacao.setNome(editNome.getText().toString());
                    newSolicitacao.setEmail(editEmail.getText().toString());
                    databaseReference.child("Solicitacao").child(newSolicitacao.getId()).setValue(newSolicitacao);
                    Toast.makeText(getApplication(),
                            "Produto solicitado com Sucesso!", Toast.LENGTH_LONG).show();
                }
            }
        });
        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(Solicitacao.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =  firebaseDatabase.getReference();
    }


}