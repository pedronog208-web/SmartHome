package DTO;

import com.example.smarthome.entidades.Comodo;

public class RotinaRequest {

    public RotinaRequest(){}

    private String nome;
    private Boolean status;

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }



    public Boolean getStatus(){
        return this.status;
    }
    public void setStatus(Boolean status){
        this.status = status;
    }


}
