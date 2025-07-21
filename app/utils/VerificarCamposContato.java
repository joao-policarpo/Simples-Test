package utils;

import dto.ContatoDTO;
import models.Contatos;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VerificarCamposContato {

    public void validarContatoDTOPost(ContatoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ContatoDTO não pode ser nulo");
        }
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do contato é obrigatório");
        }
        if (dto.getContato() == null || dto.getContato().isBlank()) {
            throw new IllegalArgumentException("Informação de contato é obrigatória");
        }
        if (dto.getProfissional() == null) {
            throw new IllegalArgumentException("Profissional associado ao contato é obrigatório");
        }

    }

    public void validarContatoDTOPut(ContatoDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ContatoDTO não pode ser nulo");
        }
        // Atualização parcial permitida: nenhum campo obrigatório
    }

    public Map<String, Object> filtrarCampos(Contatos contato, List<String> fields) {
        Map<String, Object> map = new LinkedHashMap<>();
        boolean retornarTodosCampos = (fields == null || fields.isEmpty());

        if (contato == null) return map;

        if (retornarTodosCampos || fields.contains("id")) map.put("id", contato.getId());
        if (retornarTodosCampos || fields.contains("nome")) map.put("nome", contato.getNome());
        if (retornarTodosCampos || fields.contains("contato")) map.put("contato", contato.getContato());
        if (retornarTodosCampos || fields.contains("created_date")) map.put("created_date", contato.getCreatedDate());

        if (contato.getProfissional() != null) {
            map.put("profissionalId", contato.getProfissional().getId());
        }

        return map;
    }

}