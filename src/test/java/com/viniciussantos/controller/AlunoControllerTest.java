package com.viniciussantos.controller;

import com.viniciussantos.model.Aluno;
import com.viniciussantos.repository.AlunoRepository;
import com.viniciussantos.service.AlunoService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class AlunoControllerTest {
    @LocalServerPort
    private Integer port;

//    @Container
//    @ServiceConnection
//    static   MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.33");

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine");
    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    AlunoService alunoService;

    private Aluno aluno;
    @BeforeEach
    public void setup() {
        alunoRepository.deleteAll();
        RestAssured.baseURI = "http://localhost:" + port;

        aluno = new Aluno();
        aluno.setNome("João da Silva");
        aluno.setIdade(20);
        aluno.setNumeroSala(101);
        aluno.setNomeProfessor("Professor Xavier");
        aluno.setNotaPrimeiroSemestre(7.5f);
        aluno.setNotaSegundoSemestre(8.0f);
    }
    @Test
    void testCreateAluno() {
        String alunoJson = "{ \"nome\": \"João da Silva\", \"idade\": 20 }";

        given()
                .contentType(ContentType.JSON)
                .body(alunoJson)
                .when()
                .post("api/v1/aluno")
                .then()
                .statusCode(201)
                .body("nome", equalTo("João da Silva"));
    }

    @Test
    void testGetAlunoById() {
        // Primeiro, criar um aluno para garantir que ele existe
        String alunoJson = "{ \"nome\": \"João da Silva\", \"idade\": 20 }";

        int id = given()
                .contentType(ContentType.JSON)
                .body(alunoJson)
                .when()
                .post("api/v1/aluno")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Agora, testar a obtenção do aluno pelo ID
        given()
                .pathParam("id", id)
                .when()
                .get("api/v1/aluno/{id}")
                .then()
                .statusCode(200)
                .body("nome", equalTo("João da Silva"));
    }

    @Test
    void testGetAllAlunos() {
        // Criar alguns alunos
        String alunoJson1 = "{ \"nome\": \"João da Silva\", \"idade\": 20 }";
        String alunoJson2 = "{ \"nome\": \"João da Silva\", \"idade\": 20 }";


        given().contentType(ContentType.JSON).body(alunoJson1).post("api/v1/aluno").then().statusCode(201);
        given().contentType(ContentType.JSON).body(alunoJson2).post("api/v1/aluno").then().statusCode(201);

        // Testar a listagem de todos os alunos
        given()
                .when()
                .get("api/v1/aluno")
                .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    void testUpdateAluno() {
        // Primeiro, criar um aluno para garantir que ele existe
        String alunoJson = "{ \"nome\": \"João da Silva\", \"idade\": 20 }";

        int id = given()
                .contentType(ContentType.JSON)
                .body(alunoJson)
                .when()
                .post("api/v1/aluno")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Atualizar o aluno
        String alunoJsonAtualizado = "{ \"nome\": \"João da Silva atualizado\", \"idade\": 21 }";

        given()
                .contentType(ContentType.JSON)
                .body(alunoJsonAtualizado)
                .pathParam("id", id)
                .when()
                .put("api/v1/aluno/{id}")
                .then()
                .statusCode(200)
                .body("nome", equalTo("João da Silva atualizado"));
    }

    @Test
    void testDeleteAluno() {
        // Primeiro, criar um aluno para garantir que ele existe
        String alunoJson = "{ \"nome\": \"João da Silva\", \"idade\": 20 }";

        int id = given()
                .contentType(ContentType.JSON)
                .body(alunoJson)
                .when()
                .post("api/v1/aluno")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Deletar o aluno
        given()
                .pathParam("id", id)
                .when()
                .delete("api/v1/aluno/{id}")
                .then()
                .statusCode(204);

        // Tentar buscar o aluno e esperar erro 404
        given()
                .pathParam("id", id)
                .when()
                .get("api/v1/aluno/{id}")
                .then()
                .statusCode(404);
    }
}