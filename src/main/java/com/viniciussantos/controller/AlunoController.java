package com.viniciussantos.controller;


import com.viniciussantos.dto.request.AlunoRequest;
import com.viniciussantos.dto.response.AlunoResponse;
import com.viniciussantos.model.Aluno;
import com.viniciussantos.service.AlunoService;
import com.viniciussantos.validacao.OnPatch;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Controlador REST para gerenciar operações CRUD relacionadas a alunos.
 */
@RestController
@RequestMapping("api/v1/aluno")
public class AlunoController {


    public final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    /**
     * Cria um novo aluno.
     *
     * @param alunoRequest Os dados do aluno a serem criados.
     * @return ResponseEntity contendo os dados do aluno criado e o status HTTP.
     */
    @PostMapping
    public ResponseEntity<Aluno> criar(@Valid @RequestBody AlunoRequest alunoRequest) {
//        logger.info("Iniciando a criação de um novo aluno: {}", alunoRequest.getNome());

        Aluno savedAluno = alunoService.criar(alunoRequestToAluno(alunoRequest));
        return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
    }


    /**
     * Lista todos os alunos.
     *
     * @return ResponseEntity contendo a lista de todos os alunos e o status HTTP.
     */
    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listar() {
        List<AlunoResponse> alunos = alunoService.listar()
                .stream()
                .map(this::alunoToAlunoResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }


    /**
     * Busca um aluno pelo ID.
     *
     * @param id O ID do aluno a ser buscado.
     * @return ResponseEntity contendo os dados do aluno encontrado e o status HTTP.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@Valid @PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);

        return new ResponseEntity<>(alunoToAlunoResponse(aluno), HttpStatus.OK);
    }

    /**
     * Atualiza completamente um aluno.
     *
     * @param alunoRequest Os dados do aluno a serem atualizados.
     * @param id O ID do aluno a ser atualizado.
     * @return ResponseEntity contendo os dados do aluno atualizado e o status HTTP.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@Valid @RequestBody AlunoRequest alunoRequest, @PathVariable Long id) {
        Aluno updatedAluno = alunoService.atualizar(alunoRequestToAluno(alunoRequest), id);

        return new ResponseEntity<>(alunoToAlunoResponse(updatedAluno), HttpStatus.OK);

    }


    /**
     * Atualiza parcialmente um aluno.
     *
     * @param alunoRequest Os dados parciais do aluno a serem atualizados.
     * @param id O ID do aluno a ser atualizado.
     * @return ResponseEntity contendo os dados do aluno atualizado e o status HTTP.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcialmente(@Validated(OnPatch.class) @RequestBody AlunoRequest alunoRequest, @PathVariable Long id) {
        Aluno updatedAluno = alunoService.atualizarParcialmente(alunoRequestToAluno(alunoRequest), id);

        return new ResponseEntity<>(alunoToAlunoResponse(updatedAluno), HttpStatus.OK);

    }


    /**
     * Deleta um aluno pelo ID.
     *
     * @param id O ID do aluno a ser deletado.
     * @return ResponseEntity com o status HTTP.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@Valid @PathVariable Long id) {
        alunoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Métodos privados para conversão entre AlunoRequest/Aluno e Aluno/AlunoResponse
    private Aluno alunoRequestToAluno(AlunoRequest alunoRequest) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequest.getNome());
        aluno.setIdade(alunoRequest.getIdade());
        aluno.setNomeProfessor(alunoRequest.getNomeProfessor());
        aluno.setNumeroSala(alunoRequest.getNumeroSala());
        aluno.setNotaPrimeiroSemestre(alunoRequest.getNotaPrimeiroSemestre());
        aluno.setNotaSegundoSemestre(alunoRequest.getNotaSegundoSemestre());
        return aluno;
    }
    private AlunoResponse alunoToAlunoResponse(Aluno aluno) {
        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getIdade(),
                aluno.getNomeProfessor(),
                aluno.getNumeroSala(),
                aluno.getNotaPrimeiroSemestre(),
                aluno.getNotaSegundoSemestre()
        );
    }
}
