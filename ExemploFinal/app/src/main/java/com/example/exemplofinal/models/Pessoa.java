package com.example.exemplofinal.models;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private int id;
    private String nome;

    public Pessoa() {
    }

    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static ArrayList<Pessoa> getPessoas(){
        ArrayList<Pessoa>pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(1,"Rafael Moreno"));
        pessoas.add(new Pessoa(2,"José da Silva"));
        pessoas.add(new Pessoa(3,"Maria das Dores"));

        return pessoas;
    }
}
