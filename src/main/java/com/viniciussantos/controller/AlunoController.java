package com.viniciussantos.controller;


import com.viniciussantos.model.Aluno;
import com.viniciussantos.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    public final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@Valid @RequestBody Aluno aluno) {
        Aluno savedAluno = alunoService.criar(aluno);
        return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listAlunos() {
        return new ResponseEntity<>(alunoService.listar(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findAlunoById(@Valid @PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        if (aluno == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAluno(@Valid @RequestBody Aluno aluno, @PathVariable Long id) {
        Aluno updatedAluno = alunoService.atualizar(aluno, id);
        if (updatedAluno == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedAluno, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePartialAluno(@Valid @RequestBody Aluno aluno, @PathVariable Long id) {
        Aluno updatedAluno = alunoService.atualizarParcialmente(aluno, id);
        if (updatedAluno == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedAluno, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@Valid @PathVariable Long id) {
        alunoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
