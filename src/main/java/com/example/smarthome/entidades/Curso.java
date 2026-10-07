package com.example.smarthome.entidades;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Curso {

    public Curso(){}

    public Curso(String titulo, String descricao){
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    private String descricao;

    @ManyToMany(mappedBy = "cursos")

    private Set<Usuario> alunos = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "usuariocadastro_id", nullable = true)
    private Usuario usuarioCadastro;


    public void addAluno(Usuario usuario){
        this.alunos.add(usuario);
        usuario.getCursos().add(this);

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Set<Usuario> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Usuario> alunos) {
        this.alunos = alunos;
    }

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }
}
