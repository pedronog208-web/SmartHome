package com.example.smarthome.controllers;

import DTO.LoginRequest;
import DTO.LoginResponse;
import com.example.smarthome.entidades.Usuario;
import com.example.smarthome.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/login")

public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<LoginResponse> logar(
            @RequestBody LoginRequest loginRequest) {

        if (usuarioRepository.existsUsuarioByCpfAndSenha(
                loginRequest.getLogin(),
                loginRequest.getSenha())) {

            LoginResponse resposta = new LoginResponse();
            resposta.setMensagem("Login realizado com sucesso!");

            return ResponseEntity.ok(resposta);
        }

        LoginResponse resposta = new LoginResponse();
        resposta.setMensagem("CPF ou senha inválidos.");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(resposta);
    }
}
