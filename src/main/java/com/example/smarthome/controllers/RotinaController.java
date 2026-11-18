package com.example.smarthome.controllers;

import DTO.AtualizarStatusRotinaRequest;
import DTO.RotinaRequest;
import DTO.RotinaResponse;
import com.example.smarthome.entidades.Comodo;
import com.example.smarthome.entidades.Rotina;
import com.example.smarthome.repository.RotinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotinas")
public class RotinaController {

    @Autowired
    private final RotinaRepository rotinaRepository;

    public RotinaController(RotinaRepository rotinaRepository) {
        this.rotinaRepository = rotinaRepository;
    }

    @GetMapping
    public List<Rotina> ConsultaPorRotina(){
        return rotinaRepository.findAll().stream().filter(rotina -> !"Deletado".equals(rotina.getStatus())).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rotina> ConsultarRotinaporId(@PathVariable Long id){
        var rotina = rotinaRepository.findById(id).orElse(null);

        if (rotina != null){
            return ResponseEntity.ok(rotina);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<RotinaResponse> CadastrarRotina(@RequestBody RotinaRequest rotinaRequest){
        Rotina rotinaBanco = new Rotina();

        rotinaBanco.setNome(rotinaRequest.getNome());
        rotinaBanco.setStatus(rotinaRequest.getStatus());

        rotinaRepository.save(rotinaBanco);

        return ResponseEntity.ok(new RotinaResponse(rotinaBanco.getId(), "Rotina Cadastrada com Sucesso!"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RotinaResponse> AtualizarStatusRotina(@PathVariable Long id, @RequestBody AtualizarStatusRotinaRequest atualizarStatusRotinaRequest){
        Rotina rotinaBanco = rotinaRepository.findById(id).orElse(null);

        if (rotinaBanco != null){
            rotinaBanco.setStatus(atualizarStatusRotinaRequest.getStatus());

            rotinaRepository.save(rotinaBanco);

            return ResponseEntity.ok(new RotinaResponse(rotinaBanco.getId(), "Status da Rotina atualizado com Sucesso!"));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RotinaResponse> AtualizarRotina(@PathVariable Long id, @RequestBody RotinaRequest rotinaRequest){
        Rotina rotinaBanco = rotinaRepository.findById(id).orElse(null);

        if (rotinaBanco != null){
            rotinaBanco.setNome(rotinaRequest.getNome());
            rotinaBanco.setStatus(rotinaRequest.getStatus());

            rotinaRepository.save(rotinaBanco);

            return ResponseEntity.ok(new RotinaResponse(rotinaBanco.getId(),"Rotina atualizada com Sucesso!"));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rotina> DeletarRotina(@PathVariable Long id){
        Rotina rotinaBanco = rotinaRepository.findById(id).orElse(null);

        if (rotinaBanco != null){
            rotinaBanco.setStatusDelete("Deletado");

            rotinaRepository.save(rotinaBanco);

            return ResponseEntity.ok(rotinaBanco);
        }

        return ResponseEntity.notFound().build();
    }


}
