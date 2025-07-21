package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import dto.ContatoDTO;
import services.ContatoService;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// ddefine que essa classe trata requisicoes da rota de contatos
@Tag(name = "Contatos")
public class ContatoController extends Controller {

    private final ContatoService contatoService;
    private final ObjectMapper objectMapper;

    // innnjeta o servico de contatos e o conversor json para objetos
    @Inject
    public ContatoController(ContatoService contatoService, ObjectMapper objectMapper) {
        this.contatoService = contatoService;
        this.objectMapper = objectMapper;
    }

    // retorna lista de contatos podendo aplicar filtro por texto ou selecionar campos
    @Operation(summary = "Listar contatos com ou sem filtros e campos")
    public Result listarContatos(Http.Request request) {
        String q = request.queryString().getOrDefault("q", new String[]{""})[0]; // busca texto do filtro
        String[] fieldsArray = request.queryString().get("fields");              // busca lista de campos
        List<String> fields = fieldsArray != null ? Arrays.asList(fieldsArray) : List.of(); // converte para lista

        // se nao houver campos especificos
        if (fields.isEmpty()) {
            // se houver filtro busca com filtro
            if (q != null && !q.isBlank()) {
                List<Map<String, Object>> lista = contatoService.listarContatosComFiltroECampos(q, null);
                return ok(Json.toJson(lista));
            } else {
                // sem filto retorna todos os contatos completos
                List<ContatoDTO> lista = contatoService.listarTodos();
                return ok(Json.toJson(lista));
            }
        } else {
            // retorna contatos filtrados pelos campos solicitados
            List<Map<String, Object>> listaFiltrada = contatoService.listarContatosComFiltroECampos(q, fields);
            return ok(Json.toJson(listaFiltrada));
        }
    }

    // busca um contato pelo id informado
    @Operation(summary = "Buscar contato por ID")
    public Result buscarPorId(Long id) {
        ContatoDTO dto = contatoService.buscarPorId(id);
        return ok(Json.toJson(dto));
    }

    // cria um novo contato com os dados enviados no corpo da requisicao
    @Operation(summary = "Criar novo contato")
    public Result salvar(Http.Request request) {
        JsonNode json = request.body().asJson();                        // pega json do corpo da requisicao
        ContatoDTO dto = objectMapper.convertValue(json, ContatoDTO.class); // converte json para dto
        ContatoDTO salvo = contatoService.criar(dto);                  // salva contato
        return ok("Sucesso: contato com ID " + salvo.getId() + " cadastrado.");
    }

    // atualiza os dados de um contato existente com base no id
    @Operation(summary = "Atualizar contato existente")
    public Result atualizar(Long id, Http.Request request) {
        JsonNode json = request.body().asJson();
        ContatoDTO dto = objectMapper.convertValue(json, ContatoDTO.class);
        contatoService.atualizar(id, dto);
        return ok("Sucesso: contato atualizado.");
    }

    // exclui um contato com base no id
    @Operation(summary = "Excluir contato")
    public Result excluir(Long id) {
        contatoService.deletar(id);
        return ok("Sucesso: contato excluido.");
    }
}
