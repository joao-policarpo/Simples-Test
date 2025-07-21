# 🚀 Simples-Test API

API RESTful desenvolvida com **Play Framework** (Java/Scala) para gerenciamento de entidades relacionadas a **profissionais** e seus **contatos**, com persistência em PostgreSQL, validações robustas, documentação via Swagger e suporte completo a testes automatizados. O projeto é estruturado em múltiplos módulos com arquitetura limpa e containerizado via Docker.

---

## 📚 Funcionalidades

- 🔍 Filtros e seleção de campos dinâmicos em endpoints
- 📄 CRUD de Profissionais e Contatos
- 🗃️ Relacionamento entre Contato → Profissional
- 🚫 Exclusão lógica para Profissionais (`ativo = false`)
- ⚙️ Validações via DTO + utilitários
- 🧪 Testes com JUnit + Mockito
- 📦 Documentação Swagger integrada (`/docs/index.html`)
- 🐳 Build com Docker

---

## 🛠️ Tecnologias

| Camada        | Tecnologias Utilizadas                       |
|---------------|----------------------------------------------|
| Backend       | Play Framework (Scala 2.13 / Java)           |
| ORM / DAO     | JPA + Hibernate ORM + Hibernate Validator    |
| Banco de dados| PostgreSQL 15                                |
| Build         | SBT                                          |
| JSON          | Jackson + Swagger Annotations                |
| Testes        | JUnit 5 + Mockito                            |
| Containers    | Docker + Docker Compose                      |

---

## 📦 Instalação & Execução Local

1️⃣ Iniciar o PostgreSQL localmente:
```bash

sudo systemctl start postgresql

2️⃣ Executar localmente com SBT:

bash
sbt run

3️⃣ Acessar documentação da API:

http://localhost:9000/docs/index.html

🐳 Executando com Docker
Build manual:

bash
sudo docker build -t simplestest .
sudo docker run -p 9000:9000 simplestest
Ou com Docker Compose:

bash
sudo docker compose up
Após alterações de imagem/config:

bash
sudo docker compose up --build

🔄 Endpoints REST disponíveis

Profissionais
Método	Rota	Descrição
GET	/profissionais	Lista profissionais com filtro
GET	/profissionais/{id}	Busca por ID
POST	/profissionais	Cria novo profissional
PUT	/profissionais/{id}	Atualiza profissional existente
DELETE	/profissionais/{id}	Exclusão lógica
Contatos
Método	Rota	Descrição
GET	/contatos	Lista contatos com filtros/campos
GET	/contatos/{id}	Busca contato por ID
POST	/contatos	Cria novo contato
PUT	/contatos/{id}	Atualiza contato existente
DELETE	/contatos/{id}	Remove contato

📁 Estrutura de Projeto
app/
 └── controllers/
 └── models/
 └── services/

conf/
 └── application.conf
 └── routes

docker-compose.yml
Dockerfile
Dockerfile.test
build.sbt
public/
src/
target/

📂 Módulos Internos
ContatoService: lógica de negócio, validações e persistência dos Contatos

ProfissionalService: gerenciamento de Profissionais com exclusão lógica

ContatoController: expõe operações REST, conversão JSON e Swagger

ProfissionalController: endpoints REST para profissionais, documentação

🧪 Testes Automatizados
Executados com JUnit e Mockito, isolados via container:

bash
docker-compose run test-runner
Ou localmente com SBT:

bash
sbt test
📄 Swagger UI
Documentação automática dos endpoints disponível em:

http://localhost:9000/docs/index.html
✍️ Autor
João Policarpo GitHub
