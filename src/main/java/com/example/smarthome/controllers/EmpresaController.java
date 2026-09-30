package com.example.smarthome.controllers;

import DTO.EmpresaConsultaResponse;
import DTO.EmpresaRequest;
import DTO.EmpresaResponse;
import DTO.UsuarioConsultaResponse;
import com.example.smarthome.entidades.Empresa;
import com.example.smarthome.entidades.Usuario;
import com.example.smarthome.repository.EmpresaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaRepository empresaRepository;

    public EmpresaController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @GetMapping("/cnpj/{cnpj}/usuarios")
    public ResponseEntity<List<UsuarioConsultaResponse>> buscaPorUsuariosPorCnpjEmpresa(@PathVariable String cnpj){

        var empresaBanco = empresaRepository.getEmpresaByCnpj(cnpj).orElse(null);

        if (empresaBanco == null){
            return ResponseEntity.notFound().build();
        }

        var usuarioEmpresaBanco = empresaBanco.getUsuario().stream().map(UsuarioConsultaResponse::new).toList();

        return ResponseEntity.ok(usuarioEmpresaBanco);
    }


    @PostMapping
    public ResponseEntity<EmpresaResponse> cadastrarEmpresa(@RequestBody EmpresaRequest empresaRequest){

        Empresa empresaBanco = new Empresa();

        empresaBanco.setCnpj(empresaRequest.getCnpj().replaceAll("\\D", ""));
        empresaBanco.setRazaoSocial(empresaRequest.getRazaoSocial());
        empresaBanco.setNomeFantasia(empresaRequest.getNomeFantasia());
        empresaBanco.setInscricaoEstadual(empresaRequest.getInscricaoEstadual());

        empresaRepository.save(empresaBanco);
        return ResponseEntity.ok(new EmpresaResponse(empresaBanco.getId(), "Empresa cadastrada com Sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponse> atualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaRequest empresaRequest){

        Empresa empresaBanco = empresaRepository.findById(id).orElse(null);

        if (empresaBanco == null) {
            return ResponseEntity.notFound().build();
        }

        empresaBanco.setCnpj(empresaRequest.getCnpj().replaceAll("\\D", ""));
        empresaBanco.setRazaoSocial(empresaRequest.getRazaoSocial());
        empresaBanco.setNomeFantasia(empresaRequest.getNomeFantasia());
        empresaBanco.setInscricaoEstadual(empresaRequest.getInscricaoEstadual());

        empresaRepository.save(empresaBanco);

        return ResponseEntity.ok(new EmpresaResponse(empresaBanco.getId(), "Empresa atualizada com Sucesso!"));

    }

    
    @GetMapping
    public List<EmpresaConsultaResponse> listaTodos(){
        return empresaRepository.findAll().stream().map(EmpresaConsultaResponse::new).toList();
    }


}
