package com.example.aula13.services;

import com.example.aula13.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {
    @GET("usuario")
    Call<List<Usuario>> getUsuario();
    @POST("usuario")
    Call<Usuario> postUsuario(@Body Usuario usuario);
    @DELETE("usuario/{id}")
    Call<Void> deleteUsuario(@Path("id") int idUsuario);
    @GET("usuario/{id}")
    Call<Usuario> getUsuarioPorId(@Path("id") int idUsuario);
    @PUT("usuario/{id}")
    Call<Usuario> putUsuario(@Path("id") int idUsuario,
                             @Body Usuario usuario);

}
