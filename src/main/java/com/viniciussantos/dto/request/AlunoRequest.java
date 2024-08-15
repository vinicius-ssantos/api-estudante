package com.viniciussantos.dto.request;

import jakarta.persistence.ElementCollection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
/**
 * Classe de DTO para capturar os dados de requisição de um Aluno.
 */
public class AlunoRequest {

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

    public @NotBlank(message = "O nome do aluno é obrigatório.") @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome do aluno é obrigatório.") @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "A idade é obrigatória.") @Min(value = 5, message = "A idade mínima é 5 anos.") @Max(value = 100, message = "A idade máxima é 100 anos.") Integer getIdade() {
        return idade;
    }

    public void setIdade(@NotNull(message = "A idade é obrigatória.") @Min(value = 5, message = "A idade mínima é 5 anos.") @Max(value = 100, message = "A idade máxima é 100 anos.") Integer idade) {
        this.idade = idade;
    }

    public @NotBlank(message = "O nome do professor é obrigatório.") @Size(min = 2, max = 100, message = "O nome do professor deve ter entre 2 e 100 caracteres.") String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(@NotBlank(message = "O nome do professor é obrigatório.") @Size(min = 2, max = 100, message = "O nome do professor deve ter entre 2 e 100 caracteres.") String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public @NotNull(message = "O número da sala é obrigatório.") @Positive(message = "O número da sala deve ser um valor positivo.") Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(@NotNull(message = "O número da sala é obrigatório.") @Positive(message = "O número da sala deve ser um valor positivo.") Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public @Valid List<@DecimalMin(value = "0.0", inclusive = true, message = "A nota mínima é 0.0.") @DecimalMax(value = "10.0", inclusive = true, message = "A nota máxima é 10.0.") Float> getNotasSemestre() {
        return notasSemestre;
    }

    public void setNotasSemestre(@Valid List<@DecimalMin(value = "0.0", inclusive = true, message = "A nota mínima é 0.0.") @DecimalMax(value = "10.0", inclusive = true, message = "A nota máxima é 10.0.") Float> notasSemestre) {
        this.notasSemestre = notasSemestre;
    }
// Getters e Setters
}