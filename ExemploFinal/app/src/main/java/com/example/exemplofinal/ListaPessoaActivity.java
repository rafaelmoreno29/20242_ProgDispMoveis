package com.example.exemplofinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exemplofinal.adapters.PessoaAdapter;
import com.example.exemplofinal.models.Pessoa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaPessoaActivity extends AppCompatActivity {
    RecyclerView recyclerViewPessoa;
    PessoaAdapter pessoaAdapter;
    FloatingActionButton floatingAddPessoa;

    @Override
    protected void onResume() {
        super.onResume();
        configurarRecycler();
    }

    public void configurarRecycler(){
        pessoaAdapter = new PessoaAdapter(Pessoa.getPessoas());
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_pessoa);
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