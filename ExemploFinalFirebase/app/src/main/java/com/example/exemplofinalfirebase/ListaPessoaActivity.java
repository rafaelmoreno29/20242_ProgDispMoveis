package com.example.exemplofinalfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exemplofinalfirebase.adapters.PessoaAdapter;
import com.example.exemplofinalfirebase.models.Pessoa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListaPessoaActivity extends AppCompatActivity {
    RecyclerView recyclerViewPessoa;
    PessoaAdapter pessoaAdapter;
    FloatingActionButton floatingAddPessoa;
    FirebaseFirestore db;
    ArrayList<Pessoa> pessoas;
    EditText editPesquisa;
    @Override
    protected void onResume() {
        super.onResume();
        buscarPessoas();
    }
    public void configurarRecycler(){
        pessoaAdapter = new PessoaAdapter(pessoas);
        recyclerViewPessoa.setAdapter(pessoaAdapter);
        //layout vertical
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);

        //layout horizontal
        // LinearLayoutManager layoutManager =
        //new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        //grid
        //GridLayoutManager layoutManager =
        //  new GridLayoutManager(this, 2);

        recyclerViewPessoa.setLayoutManager(layoutManager);
        recyclerViewPessoa.addItemDecoration(
                new DividerItemDecoration(this,
                        DividerItemDecoration.VERTICAL));
    }

    public void buscarPessoas(){
        pessoas = new ArrayList<>();
        if(editPesquisa.getText().toString().equals("")) {
            db.collection("pessoas").get()
                    .addOnCompleteListener(
                            new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Pessoa p = document.toObject(Pessoa.class);
                                            p.setId(document.getId());
                                            pessoas.add(p);
                                        }
                                        configurarRecycler();
                                    }
                                }
                            });
        }else{
            db.collection("pessoas").whereEqualTo("nome", editPesquisa.getText().toString()).get()
                    .addOnCompleteListener(
                            new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Pessoa p = document.toObject(Pessoa.class);
                                            p.setId(document.getId());
                                            pessoas.add(p);
                                        }
                                        configurarRecycler();
                                    }
                                }
                            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_pessoa);
        db = FirebaseFirestore.getInstance();
        editPesquisa = (EditText)findViewById(R.id.editPesquisa);
        editPesquisa.setOnEditorActionListener((v, actionId, event) -> {
            buscarPessoas();
            return true;
        });
        recyclerViewPessoa =
                (RecyclerView)findViewById(R.id.recyclerViewPessoa);
        floatingAddPessoa = (FloatingActionButton)
                findViewById(R.id.floatingAddPessoa);
        floatingAddPessoa.setOnClickListener(v -> {
            Intent intent = new Intent(ListaPessoaActivity.this,
                    PessoaDetalheActivity.class);
            startActivity(intent);
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}