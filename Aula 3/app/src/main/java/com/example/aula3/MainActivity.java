package com.example.aula3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerCidade;
    AutoCompleteTextView completeUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        spinnerCidade = (Spinner)findViewById(R.id.spinnerCidade);
        completeUf = (AutoCompleteTextView)findViewById(R.id.completeUf);
        carregarCidade();
        carregarUf();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void carregarUf() {
        String[] ufs =
                getResources().getStringArray(R.array.uf_array);
        ArrayAdapter<String> adapter =
    new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, ufs);
        completeUf.setAdapter(adapter);
    }

    private void carregarCidade() {
        String[] cidades =
                getResources().getStringArray(R.array.cidade_array);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, cidades);
        spinnerCidade.setAdapter(adapter);
    }
}