package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class SwaggerController extends Controller {
//usei o copilot para gerar a documentacao para o swagger

    public Result docs() {
        String swaggerJson = """
        {
          "openapi": "3.0.1",
          "info": {
            "title": "API de Contatos e Profissionais",
            "version": "1.0.0",
            "description": "Documentação dos endpoints REST com Play Framework"
          },
          "tags": [
            { "name": "Contatos" },
            { "name": "Profissionais" }
          ],
          "paths": {
            "/contatos": {
              "get": {
                "tags": [ "Contatos" ],
                "summary": "Listar contatos",
                "parameters": [
                  { "name": "q", "in": "query", "schema": { "type": "string" } },
                  { "name": "fields", "in": "query", "schema": { "type": "array", "items": { "type": "string" } } }
                ],
                "responses": {
                  "200": {
                    "description": "Lista de contatos",
                    "content": {
                      "application/json": {
                        "schema": { "type": "array", "items": { "$ref": "#/components/schemas/ContatoDTO" } }
                      }
                    }
                  }
                }
              },
              "post": {
                "tags": [ "Contatos" ],
                "summary": "Criar novo contato",
                "requestBody": {
                  "required": true,
                  "content": {
                    "application/json": {
                      "schema": { "$ref": "#/components/schemas/ContatoDTO" }
                    }
                  }
                },
                "responses": { "200": { "description": "Contato criado" } }
              }
            },
            "/contatos/{id}": {
              "get": {
                "tags": [ "Contatos" ],
                "summary": "Buscar contato por ID",
                "parameters": [
                  { "name": "id", "in": "path", "required": true, "schema": { "type": "integer" } }
                ],
                "responses": {
                  "200": {
                    "description": "Contato encontrado",
                    "content": {
                      "application/json": {
                        "schema": { "$ref": "#/components/schemas/ContatoDTO" }
                      }
                    }
                  }
                }
              },
              "put": {
                "tags": [ "Contatos" ],
                "summary": "Atualizar contato",
                "parameters": [
                  { "name": "id", "in": "path", "required": true, "schema": { "type": "integer" } }
                ],
                "requestBody": {
                  "required": true,
                  "content": {
                    "application/json": {
                      "schema": { "$ref": "#/components/schemas/ContatoDTO" }
                    }
                  }
                },
                "responses": { "200": { "description": "Contato atualizado" } }
              },
              "delete": {
                "tags": [ "Contatos" ],
                "summary": "Excluir contato",
                "parameters": [
                  { "name": "id", "in": "path", "required": true, "schema": { "type": "integer" } }
                ],
                "responses": { "200": { "description": "Contato excluído" } }
              }
            },
            "/profissionais": {
              "get": {
                "tags": [ "Profissionais" ],
                "summary": "Listar profissionais",
                "parameters": [
                  { "name": "q", "in": "query", "schema": { "type": "string" } },
                  { "name": "fields", "in": "query", "schema": { "type": "string" } }
                ],
                "responses": {
                  "200": {
                    "description": "Lista de profissionais",
                    "content": {
                      "application/json": {
                        "schema": { "type": "array", "items": { "$ref": "#/components/schemas/ProfissionalDTO" } }
                      }
                    }
                  }
                }
              },
              "post": {
                "tags": [ "Profissionais" ],
                "summary": "Criar novo profissional",
                "requestBody": {
                  "required": true,
                  "content": {
                    "application/json": {
                      "schema": { "$ref": "#/components/schemas/ProfissionalDTO" }
                    }
                  }
                },
                "responses": { "200": { "description": "Profissional criado" } }
              }
            },
            "/profissionais/{id}": {
              "get": {
                "tags": [ "Profissionais" ],
                "summary": "Buscar profissional por ID",
                "parameters": [
                  { "name": "id", "in": "path", "required": true, "schema": { "type": "integer" } }
                ],
                "responses": {
                  "200": {
                    "description": "Profissional encontrado",
                    "content": {
                      "application/json": {
                        "schema": { "$ref": "#/components/schemas/ProfissionalDTO" }
                      }
                    }
                  }
                }
              },
              "put": {
                "tags": [ "Profissionais" ],
                "summary": "Atualizar profissional",
                "parameters": [
                  { "name": "id", "in": "path", "required": true, "schema": { "type": "integer" } }
                ],
                "requestBody": {
                  "required": true,
                  "content": {
                    "application/json": {
                      "schema": { "$ref": "#/components/schemas/ProfissionalDTO" }
                    }
                  }
                },
                "responses": { "200": { "description": "Profissional atualizado" } }
              },
              "delete": {
                "tags": [ "Profissionais" ],
                "summary": "Excluir profissional",
                "parameters": [
                  { "name": "id", "in": "path", "required": true, "schema": { "type": "integer" } }
                ],
                "responses": { "200": { "description": "Profissional excluído" } }
              }
            }
          },
          "components": {
            "schemas": {
              "ContatoDTO": {
                "type": "object",
                "properties": {
                  "id": { "type": "integer", "example": 1 },
                  "nome": { "type": "string", "example": "Celular" },
                  "contato": { "type": "string", "example": "11988887777" },
                  "createdDate": { "type": "string", "format": "date", "example": "2025-07-15" },
                  "profissional": { "type": "integer", "example": 42 }
                }
              },
              "ProfissionalDTO": {
                "type": "object",
                "properties": {
                  "id": { "type": "integer", "example": 101 },
                  "nome": { "type": "string", "example": "João Silva" },
                  "cargo": { "type": "string", "example": "DESENVOLVEDOR" },
                  "nascimento": { "type": "string", "format": "date", "example": "1990-05-12" },
                  "createdDate": { "type": "string", "format": "date", "example": "2025-07-15" },
                  "contatos": {
                    "type": "array",
                    "items": { "$ref": "#/components/schemas/ContatoDTO" }
                  }
                }
              }
            }
          }
        }
        """;

        return ok(swaggerJson).as("application/json");
    }
}
