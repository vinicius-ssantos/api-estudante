package com.viniciussantos.service.impl;

import com.viniciussantos.exception.RecursoNaoEncontradoException;
import com.viniciussantos.model.Aluno;
import com.viniciussantos.repository.AlunoRepository;
import com.viniciussantos.service.AlunoService;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Implementação do serviço AlunoService, responsável pela lógica de negócio
 * relacionada aos Alunos.
 */
@Service
public class AlunoServiceImpl implements AlunoService {

    private AlunoRepository alunoRepository;

    private AlunoServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    /**
     * Cria um novo aluno.
     *
     * @param aluno Objeto Aluno a ser salvo.
     * @return O Aluno salvo.
     */
    public Aluno criar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    /**
     * Lista todos os alunos.
     *
     * @return Lista de todos os Alunos.
     */
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    /**
     * Busca um aluno pelo ID.
     *
     * @param id ID do aluno a ser buscado.
     * @return O Aluno encontrado ou null se não existir.
     */
    public Aluno buscarPorId(Long id) {
        return alunoRepository
                .findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Aluno não encontrado com o ID: " + id));
    }

    public Aluno atualizarParcialmente(Aluno aluno, Long id) {
        Aluno alunoEncontrado = alunoRepository.findById(id).orElse(null);
        if (alunoEncontrado == null) {
            return null;
        }
        if (aluno.getNome() != null) {
            alunoEncontrado.setNome(aluno.getNome());
        }
        if (aluno.getIdade() != null) {
            alunoEncontrado.setIdade(aluno.getIdade());
        }
        if (aluno.getNomeProfessor() != null) {
            alunoEncontrado.setNomeProfessor(aluno.getNomeProfessor());
        }
        if (aluno.getNumeroSala() != null) {
            alunoEncontrado.setNumeroSala(aluno.getNumeroSala());
        }
        if (aluno.getNotaPrimeiroSemestre() != null) {
            alunoEncontrado.setNotaPrimeiroSemestre(aluno.getNotaPrimeiroSemestre());
        }
        if (aluno.getNotaSegundoSemestre() != null) {
            alunoEncontrado.setNotaSegundoSemestre(aluno.getNotaSegundoSemestre());
        }

        return alunoRepository.save(alunoEncontrado);
    }


    /**
     * Atualiza completamente um aluno.
     *
     * @param aluno Objeto Aluno com os dados a serem atualizados.
     * @param id ID do Aluno a ser atualizado.
     * @return O Aluno atualizado.
     */
    public Aluno atualizar(Aluno aluno, Long id) {
        buscarPorId(id);
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }


    /**
     * Deleta um aluno pelo ID.
     *
     * @param id ID do Aluno a ser deletado.
     */
    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

}
