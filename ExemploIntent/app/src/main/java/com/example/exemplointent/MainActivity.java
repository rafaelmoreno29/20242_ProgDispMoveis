package com.example.exemplointent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnAbrirTela, btnAbrirMapa,btnNavegarMapa,
                btnligar, btnAbrirSite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnligar = (Button)findViewById(R.id.btnLigar);
        btnAbrirMapa =(Button)findViewById(R.id.btnAbrirMapa);
        btnAbrirSite = (Button)findViewById(R.id.btnAbrirSite);
        btnAbrirTela = (Button)findViewById(R.id.btnAbrirTela);
        btnNavegarMapa = (Button)findViewById(R.id.btnNavegarMapa);

        btnAbrirMapa.setOnClickListener(v -> {
            Uri uri =
            Uri.parse("geo:0,0?q=Avenida+general+carneiro+1427");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(mapIntent);
        });

        btnNavegarMapa.setOnClickListener(v -> {
            Uri uri =
      Uri.parse("google.navigation:q=Avenida+general+carneiro+1427");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(mapIntent);

        });

        btnAbrirSite.setOnClickListener(v -> {
            Uri webpage = Uri.parse("https://www.facens.br");
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        });

        btnligar.setOnClickListener(v -> {
            Uri uri = Uri.parse("tel:1599999999");
            Intent intent = new Intent(Intent.ACTION_CALL,uri);
            int permissionCheck =
                    ContextCompat.checkSelfPermission(
                    this, android.Manifest.permission.CALL_PHONE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.
                        requestPermissions(this,
                         new String[]{
                          android.Manifest.permission.CALL_PHONE},1);
            } else {
                startActivity(intent);
            }

        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}