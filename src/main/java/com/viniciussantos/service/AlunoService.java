package com.viniciussantos.service;

import com.viniciussantos.model.Aluno;

import java.util.List;


/**
 * Interface de serviço para operações relacionadas a Alunos.
 *
 * Esta interface define os métodos principais para gerenciar Alunos, como
 * criar, listar, buscar, atualizar e deletar.
 */
public interface AlunoService {

    public Aluno criar(Aluno aluno);

    public List<Aluno> listar();

    public Aluno buscarPorId(Long id);

    public Aluno atualizarParcialmente(Aluno aluno, Long id);

    public Aluno atualizar(Aluno aluno, Long id);

    public void deletar(Long id);
}
