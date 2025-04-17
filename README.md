
# 📚 Bookstore API

Uma API RESTful para gerenciamento de usuários e livros em uma livraria digital. Desenvolvida com Java e Spring Boot.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- Spring Security
- PostgreSQL
- JPA / Hibernate
- MapStruct
- Lombok
- Docker

## 📦 Como Executar

### Pré-requisitos

- Java 17+
- Docker
- Maven

### Usando Docker (para subir o banco de dados)

```bash
# Vá até a pasta do projeto
cd library

# Suba o container (banco de dados)
docker-compose up --build
```

### Executando

```bash
# Vá até a pasta do projeto
cd library

# Instale dependências e execute o projeto
./mvnw spring-boot:run
```

> A API estará disponível em: `http://localhost:8080`

## 📌 Funcionalidades

- Cadastro, listagem, atualização e exclusão de usuários
- Cadastro, listagem, atualização e exclusão de livros
- Aluguel e devolução de livros
- Relatórios:
    - Usuário que mais alugou livros
    - Livros alugados por determinado usuário

## 🔍 Endpoints principais

Exemplos de endpoints disponíveis:
- `POST /user/register`
- `POST /user/auth/login`
- `POST /book/register`
- `GET /book/list/all`
- `GET /book/list/available`
- `GET /book/list/rank`


## 👨‍💻 Autor

**Eduardo Rafael Bacchiega**  
[GitHub](https://github.com/dubacchiega) | [LinkedIn](https://linkedin.com/in/eduardo-rafael-bacchiega)