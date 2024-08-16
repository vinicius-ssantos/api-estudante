### 🎯 **Objetivo do Projeto**
O objetivo deste projeto é desenvolver uma API REST para gerenciar informações de estudantes, incluindo operações CRUD (Criar, Ler, Atualizar e Deletar) utilizando tecnologias modernas como Spring Boot, JPA, e banco de dados relacional.

### 🛠️ **Tecnologias Utilizadas**
- **Java**: Linguagem de programação utilizada para desenvolver a aplicação.
- **Spring Boot**: Framework utilizado para facilitar o desenvolvimento de aplicações Java, oferecendo funcionalidades prontas para a criação de APIs REST.
- **Spring Data JPA**: Facilita a integração da aplicação com o banco de dados, utilizando o padrão ORM.
- **Docker**: Ferramenta para criar e gerenciar containers, facilitando a configuração do ambiente de desenvolvimento e produção.
- **Docker Compose**: Orquestrador de containers Docker, usado para definir e gerenciar ambientes multi-container.
- **H2 Database**: Banco de dados em memória utilizado para testes.
- **JUnit e Mockito**: Bibliotecas para criação e execução de testes automatizados.
- **Maven**: Ferramenta de automação de compilação e gestão de dependências.

### 📐 **Padrões e Boas Práticas Abordados**
- **Arquitetura em Camadas**: Separação da lógica da aplicação em camadas como Controlador, Serviço, Repositório e Modelo.
- **Princípios SOLID**: Aplicação de princípios de design orientado a objetos, visando um código mais modular e fácil de manter.
- **Testes Unitários e de Integração**: Implementação de testes para garantir a qualidade e o correto funcionamento da aplicação.
- **Versionamento de Código**: Utilização de Git para controle de versões e colaboração em equipe.

### ⚙️ **Provisionamento do Ambiente**
Este projeto utiliza o Docker e Docker Compose para configurar e executar o ambiente de desenvolvimento e produção.

**Pré-requisitos**:
- Docker instalado em sua máquina.
- Docker Compose instalado.

**Passos para provisionar o ambiente**:

1. **Clonar o repositório**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd estudantes-api
   ```

2. **Construir e executar os containers**:
   ```bash
   docker-compose up --build
   ```

3. **Acessar a aplicação**:
  - A API estará disponível em `http://localhost:8080`.
  - Documentação da API (se disponível) estará em `http://localhost:8080/swagger-ui.html`.

4. **Executar Testes**:
   Para rodar os testes, utilize o Maven:
   ```bash
   mvn test
   ```

### 🚀 **Deploy**
O deploy pode ser realizado em qualquer ambiente que suporte Docker. Basta seguir os passos de provisionamento descritos acima.

### 🗂️ **Estrutura do Projeto**
```plaintext
estudantes-api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── viniciussantos/
│   │   │           ├── controller/
│   │   │           ├── dto/
│   │   │           ├── exception/
│   │   │           ├── handler/
│   │   │           ├── model/
│   │   │           ├── repository/
│   │   │           └── service/
│   │   └── resources/
│   └── test/
│       ├── java/
│       └── resources/
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

### ✍️ **Contribuição**
Sinta-se à vontade para contribuir com este projeto. Para isso:
1. Faça um fork do repositório.
2. Crie uma branch com sua feature: `git checkout -b minha-feature`.
3. Faça commit das suas mudanças: `git commit -m 'Minha nova feature'`.
4. Faça push para a branch: `git push origin minha-feature`.
5. Crie um Pull Request.

### 🛡️ **Licença**
Este projeto está licenciado sob os termos da licença MIT.

---

Esse é o README que cobre os principais aspectos do seu projeto! Caso precise de mais alguma informação ou ajuste, estou à disposição.

[Enter the Vault](https://pulsr.co.uk/Vault.html)