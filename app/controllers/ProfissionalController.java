package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import dto.ProfissionalDTO;
import services.ProfissionalService;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// esssa classe trata as requisicoes relacionadas aos profissionais
@Tag(name = "Profissionais")
public class ProfissionalController extends Controller {

    private final ProfissionalService profissionalService;
    private final ObjectMapper objectMapper;

    // injeta o servicco de profissional e o conversor json
    @Inject
    public ProfissionalController(ProfissionalService profissionalService, ObjectMapper objectMapper) {
        this.profissionalService = profissionalService;
        this.objectMapper = objectMapper;
    }

    // retorna uma lista de profissionais, podendo aplicar filtro por texto ou selecionar campos
    @Operation(summary = "Listar profissionais com ou sem filtro")
    public Result listarProfissionais(Http.Request request) {
        String q = request.getQueryString("q"); // pega valor do filtro textual

        List<String> fields = request.getQueryString("fields") != null
                ? Arrays.asList(request.getQueryString("fields").split(","))
                : null; // lista de campos especificos a serem retornados
        List<Map<String, Object>> lista = profissionalService.listarComFiltroECampos(q, fields);
        JsonNode json = objectMapper.valueToTree(lista); // converte lista para json
        return ok(json).as("application/json");
    }

    // busca um profissional com base no id informado
    @Operation(summary = "Buscar profissional por ID")
    public Result buscarPorId(Long id) {
        ProfissionalDTO dto = profissionalService.buscarPorId(id); // busca profissional
        JsonNode json = objectMapper.valueToTree(dto); // converte para json
        return ok(json).as("application/json");
    }

    // cadastra um novo profissional com os dados enviados no corpo da requisicao
    @Operation(summary = "Criar novo profissional")
    public Result salvar(Http.Request request) {
        JsonNode json = request.body().asJson(); // extrai json do corpo da requisicao
        ProfissionalDTO dto = objectMapper.convertValue(json, ProfissionalDTO.class); // converte para dto
        ProfissionalDTO salvo = profissionalService.salvar(dto); // salva profissional
        return ok("Sucesso profissional com id " + salvo.getId() + " cadastrado").as("text/plain");
    }

    // atualiza os dados de um profissional ja existente, com base no id
    @Operation(summary = "Atualizar profissional")
    public Result atualizar(Long id, Http.Request request) {
        JsonNode json = request.body().asJson(); // pega json do corpo
        ProfissionalDTO dto = objectMapper.convertValue(json, ProfissionalDTO.class); // transforma em dto
        profissionalService.atualizar(id, dto); // atualiza dados
        return ok("Sucesso profissional alterado").as("text/plain");
    }

    // exclui logicamente um profissional, ou seja, marca como inativo
    @Operation(summary = "Excluir profissional logicamente")
    public Result excluirLogicamente(Long id) {
        profissionalService.excluirLogicamente(id); // altera o campo ativo no banco
        return ok("Sucesso profissional excluido").as("text/plain");
    }
}
