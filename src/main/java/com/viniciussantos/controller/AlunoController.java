package com.viniciussantos.controller;


import com.viniciussantos.dto.request.AlunoRequest;
import com.viniciussantos.dto.response.AlunoResponse;
import com.viniciussantos.model.Aluno;
import com.viniciussantos.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    public final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Aluno> criar(@Valid @RequestBody AlunoRequest alunoRequest) {
        Aluno savedAluno = alunoService.criar(alunoRequestToAluno(alunoRequest));
        return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listar() {
        List<AlunoResponse> alunos = alunoService.listar()
                .stream()
                .map(this::alunoToAlunoResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> buscarPorId(@Valid @PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);

        return new ResponseEntity<>(alunoToAlunoResponse(aluno), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@Valid @RequestBody AlunoRequest alunoRequest, @PathVariable Long id) {
        Aluno updatedAluno = alunoService.atualizar(alunoRequestToAluno(alunoRequest), id);

        return new ResponseEntity<>(alunoToAlunoResponse(updatedAluno), HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcialmente(@Valid @RequestBody AlunoRequest alunoRequest, @PathVariable Long id) {
        Aluno updatedAluno = alunoService.atualizarParcialmente(alunoRequestToAluno(alunoRequest), id);

        return new ResponseEntity<>(alunoToAlunoResponse(updatedAluno), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@Valid @PathVariable Long id) {
        alunoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Aluno alunoRequestToAluno(AlunoRequest alunoRequest) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoRequest.getNome());
        aluno.setIdade(alunoRequest.getIdade());
        aluno.setNomeProfessor(alunoRequest.getNomeProfessor());
        aluno.setNumeroSala(alunoRequest.getNumeroSala());
        aluno.setNotasSemestre(alunoRequest.getNotasSemestre());
        return aluno;
    }
    private AlunoResponse alunoToAlunoResponse(Aluno aluno) {
        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getIdade(),
                aluno.getNomeProfessor(),
                aluno.getNumeroSala(),
                aluno.getNotasSemestre()
        );
    }
}
