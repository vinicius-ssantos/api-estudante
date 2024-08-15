package com.viniciussantos.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome do aluno é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;
    @NotNull(message = "A idade é obrigatória.")
    @Min(value = 5, message = "A idade mínima é 5 anos.")
    @Max(value = 100, message = "A idade máxima é 100 anos.")
    private Integer idade;
    @NotBlank(message = "O nome do professor é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome do professor deve ter entre 2 e 100 caracteres.")
    private String nomeProfessor;
    @NotNull(message = "O número da sala é obrigatório.")
    @Positive(message = "O número da sala deve ser um valor positivo.")
    private Integer numeroSala;
    @ElementCollection
    @Valid
    private List<@DecimalMin(value = "0.0", inclusive = true, message = "A nota mínima é 0.0.")
    @DecimalMax(value = "10.0", inclusive = true, message = "A nota máxima é 10.0.")
            Float> notasSemestre;





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
