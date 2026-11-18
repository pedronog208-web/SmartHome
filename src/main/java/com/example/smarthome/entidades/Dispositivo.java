package com.example.smarthome.entidades;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Dispositivo {

    public Dispositivo(){
    }

    public Dispositivo(String nome, String categoria, String status, Boolean conexao, double consumoWatts){
        this.nome = nome;
        this.categoria = categoria;
        this.status = status;
        this.conexao = conexao;
        this.consumoWatts = consumoWatts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;
    private String status;
    private Boolean conexao;
    private double consumoWatts;
    private LocalDateTime dataAtualizacao;
    private String statusDelete;


    @ManyToOne
    @JoinColumn(name = "comodo_id")
    private Comodo comodo;


    public Long getId(){
        return this.id;
    }


    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }



    public String getCategoria(){
        return this.categoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getConexao(){
        return this.conexao;
    }
    public void setConexao(Boolean conexao){
        this.conexao = conexao;
    }



    public double getConsumoWatts(){
        return this.consumoWatts;
    }
    public void setConsumoWatts(double consumoWatts){
        this.consumoWatts = consumoWatts;
    }



    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public String getStatusDelete() {
        return statusDelete;
    }

    public void setStatusDelete(String statusDelete) {
        this.statusDelete = statusDelete;
    }

    public Comodo getComodo() {
        return comodo;
    }

    public void setComodo(Comodo comodo) {
        this.comodo = comodo;
    }
}

