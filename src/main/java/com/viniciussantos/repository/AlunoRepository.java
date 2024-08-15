package com.viniciussantos.repository;

import com.viniciussantos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para a entidade Aluno.
 *
 * Este repositório fornece métodos de acesso aos dados dos Alunos armazenados
 * no banco de dados, como salvar, atualizar, deletar e buscar alunos.
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
