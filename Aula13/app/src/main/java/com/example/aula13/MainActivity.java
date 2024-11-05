package com.example.aula13;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aula13.models.Usuario;
import com.example.aula13.services.ApiClient;
import com.example.aula13.services.UsuarioService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    UsuarioService usuarioService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usuarioService = ApiClient.getUsuarioService();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscarUsuarios();
    }

    public void buscarUsuarios() {
   retrofit2.Call<List<Usuario>> call = usuarioService.getUsuario();
        call.enqueue(new Callback<List<Usuario>>() {
          @Override
          public void onResponse(Call<List<Usuario>> call,
                             Response<List<Usuario>> response) {
              List<Usuario> listaUsuarios = response.body();
              for (Usuario usu: listaUsuarios) {
                  Log.i("usuario", usu.getNome());
              }
          }
          @Override
         public void onFailure(Call<List<Usuario>> call, Throwable t) {
       Log.e("TESTE", "Erro ao obter os usuarios: " + t.getMessage());
          }
        });
    }
}