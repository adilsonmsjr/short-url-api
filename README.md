# 🔗 Short URL

Aplicação web para criação e gerenciamento de URLs encurtadas, permitindo transformar URLs longas em links menores e acompanhar acessos realizados.

O projeto foi desenvolvido utilizando arquitetura separada entre frontend e backend, com ambiente totalmente containerizado utilizando Docker.

---

## 🚀 Tecnologias utilizadas

### Backend

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- Flyway
- Spring Validation
- Swagger / OpenAPI
- JUnit
- Mockito
- Cache (Caffeine)

### Frontend

- React
- Vite
- JavaScript
- Tailwind CSS

### Infraestrutura

- Docker
- Docker Compose

---

# 🏗️ Arquitetura

A aplicação é dividida em dois serviços:

```
Frontend (React)
        |
        |
        v
Backend (Spring Boot)
        |
        |
        v
PostgreSQL
```

O frontend é responsável pela interface do usuário e comunicação com a API.

O backend disponibiliza os endpoints REST responsáveis por:

- criação de URLs encurtadas
- validação dos dados recebidos
- geração dos códigos únicos
- controle de expiração dos links
- registro de acessos


---

# 📦 Funcionalidades

## Criar URL encurtada

Usuário informa uma URL original e a aplicação gera um link reduzido.

Exemplo:

```
URL original:

https://www.exemplo.com/pagina/muito/grande


URL gerada:

http://localhost:8080/abc123
```


## Redirecionamento

Ao acessar o link encurtado:

```
GET /abc123
```

A aplicação localiza a URL original e realiza o redirecionamento.


## Controle de expiração

URLs possuem tempo de vida configurável.

Após o prazo definido, o link deixa de funcionar.


## Rastreamento de acessos

Cada acesso realizado é contabilizado para acompanhamento de utilização.


---

# 🐳 Executando com Docker

## Pré-requisitos

Necessário possuir instalado:

- Docker
- Docker Compose


Clone o projeto:

```bash
git clone https://github.com/adilsonmsjr/short-url-api
```

Entre na pasta:

```bash
cd short-url
```


Execute os containers:

```bash
docker compose up --build
```


A aplicação estará disponível em:

Frontend:

```
http://localhost:3000
```


Backend:

```
http://localhost:8080
```


Banco de dados:

```
localhost:5432
```


---

# 🔐 Variáveis de ambiente

Crie um arquivo `.env` seguindo o exemplo:

```
POSTGRES_DB=short_url
POSTGRES_USER=postgres
POSTGRES_PASSWORD=password

DATABASE_URL=jdbc:postgresql://postgres:5432/short_url
```


---

# 📚 Documentação da API

A documentação dos endpoints está disponível através do Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

# 📂 Estrutura do projeto

```
short-url

├── backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   └── config
│

├── frontend
│   ├── components
│   ├── pages
│   └── services

└── docker-compose.yml
```

---

# 🎯 Objetivo do projeto

Projeto desenvolvido com objetivo de aplicar conceitos de desenvolvimento backend profissional:

- APIs REST
- persistência de dados
- arquitetura em camadas
- containers Docker
- integração frontend/backend
- testes automatizados
- boas práticas de desenvolvimento

---

# 👨‍💻 Autor

Adilson Jr.

GitHub:
https://github.com/adilsonmsjr