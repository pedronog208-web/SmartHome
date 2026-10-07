package com.example.smarthome.controllers;

import DTO.CursoConsultaResponse;
import DTO.CursoRequest;
import DTO.CursoResponse;
import DTO.MatriculaRequest;
import com.example.smarthome.entidades.Curso;
import com.example.smarthome.repository.CursoRepository;
import com.example.smarthome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<CursoResponse> cadastrarCurso(@RequestBody CursoRequest cursoRequest){
        Curso cursoBanco = new Curso();

        cursoBanco.setTitulo(cursoRequest.getTitulo());
        cursoBanco.setDescricao(cursoRequest.getDescricao());


        cursoRepository.save(cursoBanco);

        return ResponseEntity.ok(new CursoResponse(cursoBanco.getId(),"Curso cadastrado com Sucesso!"));

    }


    @PostMapping("/matricula")
    public ResponseEntity<CursoResponse> matricular(@RequestBody MatriculaRequest matriculaRequest){

        var usuarioBanco = usuarioRepository.findById(matriculaRequest.getUsuario_id()).orElse(null);

        var cursoBanco = cursoRepository.findById(matriculaRequest.getCurso_id()).orElse(null);

        if (usuarioBanco == null || cursoBanco == null){
            return ResponseEntity.notFound().build();
        }

        if (cursoBanco.getAlunos().contains(usuarioBanco)){
            throw new RuntimeException("Usuário ja cadastrado nesse curso!");
        }

        cursoBanco.addAluno(usuarioBanco);

        cursoRepository.save(cursoBanco);

        return ResponseEntity.ok(new CursoResponse(cursoBanco.getId(), "Matrícula feita com Sucesso!"));

    }

    @GetMapping
    public ResponseEntity<List<CursoConsultaResponse>> listarTodos(){


        var listaCurso = cursoRepository.findAll()
                .stream()
                .map(CursoConsultaResponse::new).toList();

        return ResponseEntity.ok(listaCurso);


    }

}