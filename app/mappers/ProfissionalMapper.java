package mappers;

import dto.ProfissionalDTO;
import dto.ContatoDTO;
import models.Contatos;
import models.Profissional;

import java.util.List;
import java.util.stream.Collectors;

public class ProfissionalMapper {

    public static ProfissionalDTO toDTO(Profissional profissional) {
        if (profissional == null) return null;

        ProfissionalDTO dto = new ProfissionalDTO();
        dto.setId(profissional.getId());
        dto.setNome(profissional.getNome());
        dto.setCargo(profissional.getCargo());
        dto.setNascimento(profissional.getNascimento()); // LocalDate
        dto.setCreatedDate(profissional.getCreatedDate());

        if (profissional.getContatos() != null) {
            List<ContatoDTO> contatosDTO = profissional.getContatos().stream()
                    .map(ContatoMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setContatos(contatosDTO);
        }

        return dto;
    }

    public static Profissional toEntity(ProfissionalDTO dto) {
        if (dto == null) return null;

        Profissional profissional = new Profissional();
        profissional.setId(dto.getId());
        profissional.setNome(dto.getNome());
        profissional.setCargo(dto.getCargo());
        profissional.setNascimento(dto.getNascimento()); // LocalDate
        profissional.setCreatedDate(dto.getCreatedDate());

        if (dto.getContatos() != null) {
            List<Contatos> contatos = dto.getContatos().stream()
                    .map(ContatoMapper::toEntity)
                    .peek(c -> c.setProfissional(profissional))
                    .collect(Collectors.toList());
            profissional.setContatos(contatos);
        }

        return profissional;
    }
}
