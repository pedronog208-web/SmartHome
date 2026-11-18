package com.example.smarthome.controllers;

import DTO.ComodoRequest;
import DTO.ComodoResponse;
import com.example.smarthome.entidades.Comodo;
import com.example.smarthome.repository.ComodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comodos")

public class ComodoController {

    @Autowired
    private final ComodoRepository comodoRepository;

    public ComodoController(ComodoRepository comodoRepository) {
        this.comodoRepository = comodoRepository;
    }


    @GetMapping
    public List<Comodo> ConsultaPorComodo(){
        return comodoRepository.findAll().stream().filter(comodo -> !"Deletado".equals(comodo.getStatus())).toList();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Comodo> ConsultarComodoPorId(@PathVariable Long id){
        var comodo = comodoRepository.findById(id).orElse(null);

        if (comodo != null){
            return ResponseEntity.ok(comodo);
        }
            return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<ComodoResponse> CadastrarComodo(@RequestBody ComodoRequest comodoRequest){
        Comodo comodoBanco = new Comodo();

        comodoBanco.setNome(comodoRequest.getNome());
        comodoBanco.setCategoria(comodoRequest.getCategoria());

        comodoRepository.save(comodoBanco);

        return ResponseEntity.ok(new ComodoResponse(comodoBanco.getId(), "Cômodo Cadastrado com Sucesso!"));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ComodoResponse> AtualizarComodo(@PathVariable Long id, @RequestBody  ComodoRequest comodoRequest){

        Comodo comodoBanco = comodoRepository.findById(id).orElse(null);

        if (comodoBanco != null) {
            comodoBanco.setNome(comodoRequest.getNome());
            comodoBanco.setCategoria(comodoRequest.getCategoria());
            comodoBanco.setDataAtualizacao(LocalDateTime.now());

            comodoRepository.save(comodoBanco);

            return ResponseEntity.ok(new ComodoResponse(comodoBanco.getId(), "Cômodo atualizado com Sucesso!"));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comodo> DeletarComodo(@PathVariable Long id){

        Comodo comodoBanco = comodoRepository.findById(id).orElse(null);

        if (comodoBanco != null){

            comodoBanco.setStatus("Deletado");

            comodoRepository.save(comodoBanco);

            return ResponseEntity.ok(comodoBanco);
        }

        return ResponseEntity.notFound().build();
    }



}
