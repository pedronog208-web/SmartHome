package com.example.smarthome.controllers;

import DTO.AtualizarConexaoDispositivoRequest;
import DTO.AtualizarStatusDispositivoRequest;
import DTO.DispositivoRequest;
import DTO.DispositivoResponse;
import com.example.smarthome.entidades.Comodo;
import com.example.smarthome.entidades.Dispositivo;
import com.example.smarthome.repository.ComodoRepository;
import com.example.smarthome.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/dispositivos")
public class DispositivoController{

    @Autowired
    private  DispositivoRepository dispositivoRepository;
    @Autowired
    private ComodoRepository comodoRepository;


    @GetMapping
    public List<Dispositivo> ConsultaDispositivo(){
        return dispositivoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> ConsultarDispositivoPorId(@PathVariable Long id){
        var dispositivo = dispositivoRepository.findById(id).orElse(null);

        if (dispositivo != null){
            return ResponseEntity.ok(dispositivo);
        }
        return ResponseEntity.notFound().build();
    }



    @PostMapping
    public ResponseEntity<DispositivoResponse> CadastrarDispositivo(@RequestBody DispositivoRequest dispositivoRequest){


        Dispositivo dispositivoBanco = new Dispositivo();

        Comodo comodoBanco = comodoRepository
                .findById(dispositivoRequest.getComodoId())
                .orElse(null);

        if (comodoBanco != null) {
            dispositivoBanco.setNome(dispositivoRequest.getNome());
            dispositivoBanco.setCategoria(dispositivoRequest.getCategoria());
            dispositivoBanco.setStatus(dispositivoRequest.getStatus());
            dispositivoBanco.setConexao(dispositivoRequest.getConexao());
            dispositivoBanco.setConsumoWatts(dispositivoRequest.getConsumoWatts());
            dispositivoBanco.setComodo(comodoBanco);

            dispositivoRepository.save(dispositivoBanco);

            return ResponseEntity.ok(new DispositivoResponse(dispositivoBanco.getId(), "Dispositivo Cadastrado com Sucesso!"));
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DispositivoResponse> AtualizarStatusDispositivo(@PathVariable Long id, @RequestBody AtualizarStatusDispositivoRequest dispositivoRequest){

        Dispositivo dispositivoBanco = dispositivoRepository.findById(id).orElse(null);

        if (dispositivoBanco != null){
            dispositivoBanco.setStatus(dispositivoRequest.getStatus());
            dispositivoBanco.setDataAtualizacao(LocalDateTime.now());

            dispositivoRepository.save(dispositivoBanco);

            return ResponseEntity.ok(new DispositivoResponse(dispositivoBanco.getId(), "Status do Dispositivo atualizado com Sucesso!"));
        }

        return ResponseEntity.notFound().build();

    }

    @PatchMapping("/{id}/conexao")
    public ResponseEntity<DispositivoResponse> AtualizarConexaoDispositivo(@PathVariable Long id, @RequestBody AtualizarConexaoDispositivoRequest dispositivoRequest){

        Dispositivo dispositivoBanco = dispositivoRepository.findById(id).orElse(null);

        if (dispositivoBanco != null){
            dispositivoBanco.setConexao(dispositivoRequest.getConexao());

            dispositivoRepository.save(dispositivoBanco);

            return ResponseEntity.ok(new DispositivoResponse(dispositivoBanco.getId(), "Conexão do Dispositivo atualizada com Sucesso!"));

        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<DispositivoResponse> AtualizarDispositivo(@PathVariable Long id, @RequestBody DispositivoRequest dispositivoRequest){

        Dispositivo dispositivoBanco = dispositivoRepository.findById(id).orElse(null);

        if (dispositivoBanco != null){

                dispositivoBanco.setNome(dispositivoRequest.getNome());
                dispositivoBanco.setCategoria(dispositivoRequest.getCategoria());
                dispositivoBanco.setStatus(dispositivoRequest.getStatus());
                dispositivoBanco.setConexao(dispositivoRequest.getConexao());
                dispositivoBanco.setConsumoWatts(dispositivoRequest.getConsumoWatts());
                dispositivoBanco.setDataAtualizacao(LocalDateTime.now());

                dispositivoRepository.save(dispositivoBanco);

            return ResponseEntity.ok(new DispositivoResponse(dispositivoBanco.getId(), "Dispositivo atualizado com Sucesso!"));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<DispositivoResponse> DeletarDispositivo(@PathVariable Long id){

        Dispositivo dispositivoBanco = dispositivoRepository.findById(id).orElse(null);
            if (dispositivoBanco != null){
                    dispositivoBanco.setStatusDelete("D");
                    dispositivoRepository.save(dispositivoBanco);
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();
    }



}