package com.viniciussantos.controller;



import com.viniciussantos.dto.request.AlunoRequest;
import com.viniciussantos.dto.response.AlunoResponse;
import com.viniciussantos.model.Aluno;
import com.viniciussantos.service.AlunoService;
import com.viniciussantos.validacao.OnPatch;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Controlador REST para gerenciar operações CRUD relacionadas a alunos.
 */

@RestController
@RequestMapping("api/v1/aluno")
@Tag(name = "Aluno", description = "Gerenciamento de alunos")
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

    @Operation(summary = "Cria um novo aluno", description = "Adiciona um novo aluno ao sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso",
                    content = @Content(schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Aluno> criar(@Valid @RequestBody  AlunoRequest alunoRequest) {

        Aluno savedAluno = alunoService.criar(alunoRequestToAluno(alunoRequest));
        return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
    }


    /**
     * Lista todos os alunos.
     *
     * @return ResponseEntity contendo a lista de todos os alunos e o status HTTP.
     */

    @Operation(summary = "Lista todos os alunos", description = "Retorna uma lista com todos os alunos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = Aluno.class)))
    })
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

    @Operation(summary = "Busca um aluno pelo ID", description = "Retorna um aluno específico com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                    content = @Content)
    })
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

    @Operation(summary = "Atualiza completamente um aluno", description = "Atualiza completamente um aluno com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@Valid  @RequestBody AlunoRequest alunoRequest,  @PathVariable Long id) {
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

    @Operation(summary = "Atualiza parcialmente um aluno", description = "Atualiza parcialmente um aluno com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                    content = @Content)
    })
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

    @Operation(summary = "Deleta um aluno pelo ID", description = "Deleta um aluno com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno deletado com sucesso",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@Valid  @PathVariable Long id) {
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
