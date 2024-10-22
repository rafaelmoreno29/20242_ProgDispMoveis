package com.example.exemplofinalfirebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PessoaDetalheActivity extends AppCompatActivity {
    EditText editNome, editCelular, editEmail;
    Button btnSalvar;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pessoa_detalhe);
        btnSalvar = (Button)findViewById(R.id.buttonSalvar);
        editNome = (EditText)findViewById(R.id.editTextNome);
        editEmail = (EditText)findViewById(R.id.editTextEmail);
        editCelular = (EditText)findViewById(R.id.editTextCelular);
        if(getIntent().hasExtra(id))
            id = getIntent().getStringExtra("id");




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}