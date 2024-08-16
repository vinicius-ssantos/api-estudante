package com.viniciussantos.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.viniciussantos.ContainersConfig;
import com.viniciussantos.model.Aluno;
import com.viniciussantos.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@DataJpaTest(properties = {
        "spring.test.database.replace=none",
        "spring.datasource.url=jdbc:tc:mysql:8.0.33:///"
})
@Import(ContainersConfig.class)
class AlunoServiceImplTest {
    @InjectMocks
    private AlunoServiceImpl alunoService;

    @Mock
    private AlunoRepository alunoRepository;
    Aluno aluno = new Aluno();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        aluno = new Aluno();
        aluno.setNome("João da Silva");
        aluno.setIdade(20);
        aluno.setNumeroSala(101);
        aluno.setNomeProfessor("Professor Xavier");
        aluno.setNotaPrimeiroSemestre(7.5F);
        aluno.setNotaSegundoSemestre(8.0F);
    }

    @Test
    void testCreateAluno() {

        Mockito.when(alunoRepository.save(Mockito.any(Aluno.class))).thenReturn(aluno);

        Aluno alunoCriado = alunoService.criar(aluno);

        assertNotNull(alunoCriado);
        assertEquals("João da Silva", alunoCriado.getNome());
    }

    @Test
    void testGetAlunoById() {


        Mockito.when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        Aluno foundAluno = alunoService.buscarPorId(1L);

        assertNotNull(foundAluno);
        assertEquals("João da Silva", foundAluno.getNome());
    }

    @Test
    void testGetAllAlunos() {


        List<Aluno> alunos = List.of(aluno, aluno);

        Mockito.when(alunoRepository.findAll()).thenReturn(alunos);

        List<Aluno> foundAlunos = alunoService.listar();

        assertEquals(2, foundAlunos.size());
        assertTrue(foundAlunos.stream().anyMatch(aluno -> aluno.getNome().equals("João da Silva")));
        assertTrue(foundAlunos.stream().anyMatch(aluno -> aluno.getNome().equals("João da Silva")));
    }

    @Test
    void testUpdateAluno() {

        Mockito.when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        Mockito.when(alunoRepository.save(Mockito.any(Aluno.class))).thenReturn(aluno);

        Aluno updatedAluno = alunoService.atualizar(aluno, 1L);

        assertNotNull(updatedAluno);
        assertEquals("João da Silva", updatedAluno.getNome());
    }

    @Test
    void testDeleteAluno() {

        Mockito.when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        Mockito.doNothing().when(alunoRepository).deleteById(1L);

        alunoService.deletar(1L);

        Mockito.verify(alunoRepository, Mockito.times(1)).deleteById(1L);
    }
}