package com.viniciussantos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Classe principal da aplicação Spring Boot.
 *
 * Esta classe inicia a aplicação, configurando o contexto Spring e lançando
 * o servidor embutido.
 */
@SpringBootApplication
public class EstudantesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstudantesApiApplication.class, args);
    }

}
