package com.viniciussantos.service.impl;

import com.viniciussantos.model.Aluno;
import com.viniciussantos.repository.AlunoRepository;
import com.viniciussantos.service.AlunoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {

    public AlunoRepository alunoRepository;

    public AlunoServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno criar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }


    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
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
        if (aluno.getNotasSemestre() != null) {
            alunoEncontrado.setNotasSemestre(aluno.getNotasSemestre());
        }

        return alunoRepository.save(alunoEncontrado);
    }

    public Aluno atualizar(Aluno aluno, Long id) {
        buscarPorId(id);
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

}
