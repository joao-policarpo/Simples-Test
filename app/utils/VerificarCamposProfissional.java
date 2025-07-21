package utils;

import dto.ProfissionalDTO;
import models.Profissional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VerificarCamposProfissional {

    public void validarProfissionalDTOPost(ProfissionalDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("ProfissionalDTO não pode ser nulo");
        }
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do profissional é obrigatório");
        }
        if (dto.getCargo() == null) {
            throw new IllegalArgumentException("Cargo do profissional é obrigatório");
        }
        if (dto.getNascimento() == null) {
            throw new IllegalArgumentException("Data de nascimento do profissional é obrigatória");
        }
    }

    public void aplicarCamposSePresentes(ProfissionalDTO dto, Profissional entidade) {
        if (dto.getNome() != null) entidade.setNome(dto.getNome());
        if (dto.getCargo() != null) entidade.setCargo(dto.getCargo());
        if (dto.getNascimento() != null) entidade.setNascimento(dto.getNascimento());
    }

    public Map<String, Object> filtrarCamposeSerializar(Profissional profissional, List<String> fields, boolean retornarTodosCampos) {
        Map<String, Object> map = new LinkedHashMap<>();

        if (profissional == null) return map;

        if (retornarTodosCampos || fields.contains("id")) map.put("id", profissional.getId());
        if (retornarTodosCampos || fields.contains("nome")) map.put("nome", profissional.getNome());
        if (retornarTodosCampos || fields.contains("cargo")) map.put("cargo", profissional.getCargo());
        if (retornarTodosCampos || fields.contains("nascimento")) map.put("nascimento", profissional.getNascimento());
        if (retornarTodosCampos || fields.contains("created_date")) map.put("created_date", profissional.getCreatedDate());

        if (retornarTodosCampos || fields.contains("contatos")) {
            if (profissional.getContatos() != null) {
                List<Map<String, Object>> contatosList = profissional.getContatos().stream()
                        .map(contato -> {
                            Map<String, Object> contatoMap = new LinkedHashMap<>();
                            contatoMap.put("id", contato.getId());
                            contatoMap.put("nome", contato.getNome());
                            contatoMap.put("contato", contato.getContato());
                            contatoMap.put("createdDate", contato.getCreatedDate());
                            contatoMap.put("profissional", profissional.getId());
                            return contatoMap;
                        })
                        .collect(Collectors.toList());
                map.put("contatos", contatosList);
            }
        }

        return map;
    }

}
