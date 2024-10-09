package com.example.exemplofinal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exemplofinal.adapters.PessoaAdapter;
import com.example.exemplofinal.models.Pessoa;

public class ListaPessoaActivity extends AppCompatActivity {
    RecyclerView recyclerViewPessoa;
    PessoaAdapter pessoaAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        configurarRecycler();
    }

    public void configurarRecycler(){
        pessoaAdapter = new PessoaAdapter(Pessoa.getPessoas());
        recyclerViewPessoa.setAdapter(pessoaAdapter);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}