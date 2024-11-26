package com.example.exemploautenticacao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail, txtSenha;
    TextView txtRegistar;
    Button btnEntrar;
    FirebaseAuth auth;
    ProgressBar progressBar;
    CheckBox ckLembrar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.editTextEmail);
        txtSenha = (EditText) findViewById(R.id.editTextSenha);
        btnEntrar = (Button) findViewById(R.id.buttonEntrar);
        txtRegistar = (TextView) findViewById(R.id.TextViewRegistrar);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        ckLembrar = (CheckBox)findViewById(R.id.checkBoxLembrar);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        btnEntrar.setOnClickListener(v -> {
            entrar();
        });
        txtRegistar.setOnClickListener(v -> {
            registrar();
        });

        auth = FirebaseAuth.getInstance();

        if(sharedPreferences.contains("email")){
            txtEmail.setText(sharedPreferences.getString("email",""));
            txtSenha.setText(sharedPreferences.getString("senha",""));
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }
    }
    public void entrar(){
        progressBar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(txtEmail.getText().toString(),txtSenha.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            if (ckLembrar.isChecked()) {
                                editor.putString("email", txtEmail.getText().toString());
                                editor.putString("senha", txtSenha.getText().toString());
                            } else {
                                editor.remove("email");
                                editor.remove("senha");
                            }
                            editor.apply();


                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Falha na autenticação.",Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
    public void registrar(){
        Intent i = new Intent(getApplicationContext(),RegistrarActivity.class);
        startActivity(i);
        finish();
    }

}