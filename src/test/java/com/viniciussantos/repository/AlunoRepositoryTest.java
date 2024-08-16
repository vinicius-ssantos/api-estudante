package com.viniciussantos.repository;


import com.viniciussantos.ContainersConfig;
import com.viniciussantos.model.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest(properties = {
        "spring.test.database.replace=none",
        "spring.datasource.url=jdbc:tc:mysql:8.0.33:///"
})
@Import(ContainersConfig.class)
public class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    private Aluno aluno;
    @BeforeEach
    public void setup() {
        aluno = new Aluno();
        aluno.setNome("João da Silva");
        aluno.setIdade(20);
        aluno.setNumeroSala(101);
        aluno.setNomeProfessor("Professor Xavier");
        aluno.setNotaPrimeiroSemestre(7.5f);
        aluno.setNotaSegundoSemestre(8.0f);
    }


    @Test
    public void testSaveAluno() {
        Aluno savedAluno = alunoRepository.save(aluno);
        assertThat(savedAluno).isNotNull();
        assertThat(savedAluno.getId()).isNotNull();
    }

    @Test
    public void testFindById() {
        Aluno savedAluno = alunoRepository.save(aluno);
        Optional<Aluno> foundAluno = alunoRepository.findById(savedAluno.getId());
        assertThat(foundAluno).isPresent();
        assertThat(foundAluno.get().getNome()).isEqualTo("João da Silva");
    }

    @Test
    public void testFindAll() {
        alunoRepository.save(aluno);
        Iterable<Aluno> alunos = alunoRepository.findAll();
        assertThat(alunos).hasSize(1);
    }

    @Test
    public void testUpdateAluno() {
        Aluno savedAluno = alunoRepository.save(aluno);
        savedAluno.setNome("João Pedro da Silva");
        Aluno updatedAluno = alunoRepository.save(savedAluno);

        assertThat(updatedAluno.getNome()).isEqualTo("João Pedro da Silva");
    }

    @Test
    public void testDeleteById() {
        Aluno savedAluno = alunoRepository.save(aluno);
        alunoRepository.deleteById(savedAluno.getId());

        Optional<Aluno> deletedAluno = alunoRepository.findById(savedAluno.getId());
        assertThat(deletedAluno).isNotPresent();
    }

}
