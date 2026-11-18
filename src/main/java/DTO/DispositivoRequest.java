package DTO;

import com.example.smarthome.entidades.Comodo;
import com.example.smarthome.entidades.Dispositivo;

public class DispositivoRequest {

    public DispositivoRequest(){}

    private String nome;
    private String categoria;
    private String status;
    private Boolean conexao;
    private double consumoWatts;
    private Long comodoId;

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


    public Long getComodoId() {
        return comodoId;
    }

    public void setComodoId(Long comodoId) {
        this.comodoId = comodoId;
    }
}
