package com.example.aula2_calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edt1,edt2,edtRes;
    Button btnsomar,btnsubtrair,btblimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        buscarComponentes();

        btnsomar.setOnClickListener(v -> {
            double n1 = Double.parseDouble(edt1.getText().toString());
            double n2 = Double.parseDouble(edt1.getText().toString());
            double resultado = n1 + n2;
            edtRes.setText("" + resultado);
        });
        btnsubtrair.setOnClickListener(v -> {
            double n1 = Double.parseDouble(edt1.getText().toString());
            double n2 = Double.parseDouble(edt1.getText().toString());
            double resultado = n1 - n2;
            edtRes.setText("" + resultado);
        });
        btblimpar.setOnClickListener(v -> {
            edt1.setText("");
            edt2.setText("");
            edtRes.setText("");
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void buscarComponentes() {
        edt1 = (EditText)findViewById(R.id.editNum1);
        edt2 = (EditText)findViewById(R.id.editNum2);
        edtRes = (EditText)findViewById(R.id.editResultado);
        btnsomar = (Button) findViewById(R.id.buttonSomar);
        btnsubtrair = (Button) findViewById(R.id.buttonSubtrair);
        btblimpar = (Button) findViewById(R.id.buttonLimpar);
    }
}