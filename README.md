### **README.md**

# Projeto de Gerenciamento de Alunos - API RESTful

## **Descrição do Projeto**

Este projeto consiste em uma API RESTful desenvolvida em **Java** utilizando o **Spring Boot**. A API foi projetada para gerenciar dados de alunos em uma escola, permitindo operações CRUD (Create, Read, Update, Delete). A API oferece suporte para validações de dados, atualizações parciais usando o método HTTP PATCH, e inclui todos os componentes necessários para a persistência dos dados.

## **Tecnologias Utilizadas**

- **Java 17**
- **Spring Boot 2.7.x**
- **Spring Data JPA**
- **Hibernate Validator**
- **MySQL**
- **Lombok** (Opcional, para reduzir o boilerplate de código)
- **Maven**

## **Funcionalidades Implementadas**

### **Modelo (Entity) - `Aluno`**

A entidade `Aluno` representa os dados de um aluno e possui os seguintes campos:

- **`id`**: Identificador único do aluno (auto-gerado pelo banco de dados).
- **`nome`**: Nome do aluno. Validação:
    - **@NotBlank**: O nome é obrigatório.
    - **@Size(min = 2, max = 100)**: O nome deve ter entre 2 e 100 caracteres.
- **`idade`**: Idade do aluno. Validação:
    - **@NotNull**: A idade é obrigatória.
    - **@Min(5)**: Idade mínima é de 5 anos.
    - **@Max(100)**: Idade máxima é de 100 anos.
- **`nomeProfessor`**: Nome do professor responsável. Validação:
    - **@NotBlank**: O nome do professor é obrigatório.
    - **@Size(min = 2, max = 100)**: O nome do professor deve ter entre 2 e 100 caracteres.
- **`numeroSala`**: Número da sala onde o aluno estuda. Validação:
    - **@NotNull**: O número da sala é obrigatório.
    - **@Positive**: O número da sala deve ser um valor positivo.
- **`notasSemestre`**: Lista de notas do semestre. Validação:
    - **@ElementCollection**: Indica que uma coleção de valores básicos (Float) está embutida na entidade `Aluno`.
    - **@DecimalMin(0.0)** e **@DecimalMax(10.0)**: Cada nota deve estar entre 0.0 e 10.0.

### **Repositório - `AlunoRepository`**

- **Interface** que estende `JpaRepository<Aluno, Long>`, fornecendo métodos CRUD básicos automaticamente.

### **Serviço - `AlunoService` e `AlunoServiceImpl`**

- **Interface `AlunoService`**: Define os métodos de serviço para manipulação dos dados de `Aluno`.
- **Classe `AlunoServiceImpl`**: Implementa os métodos definidos na interface, contendo a lógica de negócios para as operações CRUD e suporte a atualizações parciais com o método PATCH.

### **Controlador - `AlunoController`**

- **Controller REST** que mapeia as operações CRUD:
    - `POST /alunos`: Criação de um novo aluno.
    - `GET /alunos`: Recuperação de todos os alunos.
    - `GET /alunos/{id}`: Recuperação de um aluno específico pelo seu ID.
    - `PUT /alunos/{id}`: Atualização completa dos dados de um aluno.
    - `PATCH /alunos/{id}`: Atualização parcial dos dados de um aluno.
    - `DELETE /alunos/{id}`: Exclusão de um aluno.

### **Validações**

As validações são aplicadas para garantir que os dados recebidos pela API estejam no formato esperado. Isso é essencial para manter a integridade dos dados dentro do sistema.

- Para a criação de um novo aluno (`POST`), validações estritas são aplicadas para garantir que todos os campos obrigatórios estejam presentes e corretos.
- Para atualizações parciais (`PATCH`), as validações permitem campos nulos, indicando que esses campos não devem ser atualizados.

## **Execução do Projeto**

### **Pré-requisitos**

- **Java 17** instalado.
- **Maven** instalado.
- **MySQL** instalado e configurado.

### **Configuração do Banco de Dados**

Configure as propriedades de conexão ao banco de dados no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/escola
spring.datasource.username=usuario
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

Certifique-se de que o banco de dados `escola` esteja criado no MySQL.

### **Compilação e Execução**

1. **Compilar o Projeto**:
    - No diretório raiz do projeto, execute:
      ```bash
      mvn clean install
      ```

2. **Executar a Aplicação**:
    - Execute a aplicação com o comando:
      ```bash
      mvn spring-boot:run
      ```

3. **Acesso à API**:
    - A aplicação estará disponível em `http://localhost:8080`.

### **Testando a API**

Você pode testar a API usando ferramentas como **Postman** ou **cURL**. Exemplos de requisições:

- **Criação de um Aluno**:
  ```http
  POST /alunos
  Content-Type: application/json

  {
      "nome": "João Souza",
      "idade": 16,
      "nomeProfessor": "Prof. Maria",
      "numeroSala": 102,
      "notasSemestre": [9.0, 8.0, 9.5]
  }
  ```

- **Atualização Parcial de um Aluno**:
  ```http
  PATCH /alunos/1
  Content-Type: application/json

  {
      "idade": 17
  }
  ```

- **Recuperar Todos os Alunos**:
  ```http
  GET /alunos
  ```

- **Excluir um Aluno**:
  ```http
  DELETE /alunos/1
  ```

## **Considerações Finais**

Este projeto é uma base sólida para o desenvolvimento de sistemas de gerenciamento de alunos em uma escola. Ele pode ser expandido para incluir mais funcionalidades, como autenticação, autorização, e integração com outros serviços.

Se você tiver alguma dúvida ou problema, sinta-se à vontade para abrir uma _issue_ ou contribuir com melhorias no código.

---

Espero que este README ajude a esclarecer o propósito do projeto, como ele foi construído e como ele pode ser utilizado e expandido no futuro. Se precisar de mais alguma coisa, estou à disposição! 📄