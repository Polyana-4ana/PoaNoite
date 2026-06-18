# POANOITE

Plataforma web para descoberta de eventos noturnos em Porto Alegre. Usuários publicam, avaliam e interagem com posts sobre festas, bares e shows da cidade.

Desenvolvido como projeto acadêmico na disciplina de Arquitetura de Software — Unisinos 2026/1.

## Tecnologias

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA + Hibernate
- PostgreSQL
- Lombok
- Swagger (SpringDoc OpenAPI)
- Maven

## Pré-requisitos

- Java 21 instalado
- PostgreSQL instalado e rodando na porta 5432
- pgAdmin (opcional, para administrar o banco)

## Configuração do banco de dados

1. Crie o banco de dados no pgAdmin ou via psql:

```sql
CREATE DATABASE poanoite_db;
```

2. Execute o script SQL disponível em `docs/schema.sql` para criar as tabelas e inserir os dados de exemplo.

## Configuração da aplicação

Crie o arquivo `src/main/resources/application.properties` com o seguinte conteúdo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/poanoite_db
spring.datasource.username=postgres
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true

server.port=8080
```

## Como executar

1. Clone o repositório
2. Abra o projeto no IntelliJ IDEA
3. Configure o `application.properties` conforme acima
4. Execute a classe `PoanoiteApplication.java`
5. Acesse a documentação da API em:

```
http://localhost:8080/swagger-ui/index.html
```

## Dados de exemplo

O script SQL insere dois usuários e três locais de evento para testes:

| Campo | Valor |
|---|---|
| Email Gabriel | gabriel@poanoite.com |
| Email Polyana | polyana@poanoite.com |
| Senha (ambos) | Senha123 |

## Autores

Gabriel Lacerda e Polyana Santos