package com.viniciussantos.dto.response;

import java.util.List;
/**
 * Classe de DTO para enviar os dados de resposta de um Aluno.
 */
public class AlunoResponse {

    private Long id;
    private String nome;
    private Integer idade;
    private String nomeProfessor;
    private Integer numeroSala;
    private Float notaPrimeiroSemestre;
    private Float notaSegundoSemestre;

    // Construtores, Getters e Setters

    public AlunoResponse(Long id, String nome, Integer idade, String nomeProfessor, Integer numeroSala, Float notaPrimeiroSemestre, Float notaSegundoSemestre) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.nomeProfessor = nomeProfessor;
        this.numeroSala = numeroSala;
        this.notaPrimeiroSemestre = notaPrimeiroSemestre;
        this.notaSegundoSemestre = notaSegundoSemestre;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Float getNotaPrimeiroSemestre() {
        return notaPrimeiroSemestre;
    }

    public void setNotaPrimeiroSemestre(Float notaPrimeiroSemestre) {
        this.notaPrimeiroSemestre = notaPrimeiroSemestre;
    }
    public Float getNotaSegundoSemestre() {
        return notaSegundoSemestre;
    }

    public void setNotaSegundoSemestre(Float notaSegundoSemestre) {
        this.notaSegundoSemestre = notaSegundoSemestre;
    }
}
