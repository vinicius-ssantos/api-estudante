package com.viniciussantos.controller;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do Swagger.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configuração do Swagger.
     *
     * @return Configuração do Swagger.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Estudantes API")
                        .version("1.0")
                        .description("Documentação da API para gerenciamento de estudantes"));
    }



}
