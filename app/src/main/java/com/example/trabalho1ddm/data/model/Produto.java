package com.example.trabalho1ddm.data.model;

import java.io.Serializable;

public class Produto  implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private double preco;
    private boolean ativo;
    private String imagem;

    public Produto() {}

    public Produto(String titulo, double preco, boolean ativo, int id, String imagem) {
        this.titulo = titulo;
        this.preco = preco;
        this.ativo = ativo;
        this.id = id;
        this.imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
