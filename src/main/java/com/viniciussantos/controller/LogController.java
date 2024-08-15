package com.viniciussantos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
/**
 * Controlador REST para acessar e filtrar logs da aplicação.
 */
@RestController
@RequestMapping("/logs")
public class LogController {

    @Value("${logging.file.name:logs/application.log}")
    private String logFilePath;

    /**
     * Retorna as últimas linhas do arquivo de log.
     *
     * @param lines Número de linhas do final do log a serem retornadas.
     * @return ResponseEntity contendo as linhas do log e o status HTTP.
     */
    @GetMapping
    public ResponseEntity<?> getLogs(@RequestParam(required = false, defaultValue = "100") int lines) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(logFilePath));
            int fromIndex = Math.max(0, allLines.size() - lines);
            List<String> lastLines = allLines.subList(fromIndex, allLines.size());
            return new ResponseEntity<>(lastLines, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Erro ao ler o arquivo de log: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
