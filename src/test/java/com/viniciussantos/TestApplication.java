package com.viniciussantos;

import com.viniciussantos.config.ContainersConfig;
import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication
                .from(EstudantesApiApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
