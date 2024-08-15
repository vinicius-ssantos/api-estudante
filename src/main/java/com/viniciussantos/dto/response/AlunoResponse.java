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
    private List<Float> notasSemestre;

    // Construtores, Getters e Setters

    public AlunoResponse(Long id, String nome, Integer idade, String nomeProfessor, Integer numeroSala, List<Float> notasSemestre) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.nomeProfessor = nomeProfessor;
        this.numeroSala = numeroSala;
        this.notasSemestre = notasSemestre;
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

    public List<Float> getNotasSemestre() {
        return notasSemestre;
    }

    public void setNotasSemestre(List<Float> notasSemestre) {
        this.notasSemestre = notasSemestre;
    }
}
