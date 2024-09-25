package com.example.exemplointent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CompartilharActivity extends AppCompatActivity {
    TextView txtMensagem;
    Button btnVoltar, btnCompartilhar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compartilhar);
        txtMensagem = (TextView)findViewById(R.id.editTextTextMultiLine);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnCompartilhar = (Button)findViewById(R.id.btnCompartilhar);

        btnVoltar.setOnClickListener(v -> {
            finish();
        });
        btnCompartilhar.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
     sendIntent.putExtra(Intent.EXTRA_TEXT, txtMensagem.getText().toString());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}