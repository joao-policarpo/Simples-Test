package mappers;

import dto.ContatoDTO;
import models.Contatos;

public class ContatoMapper {

    public static ContatoDTO toDTO(Contatos contato) {
        if (contato == null) return null;

        ContatoDTO dto = new ContatoDTO();
        dto.setId(contato.getId());
        dto.setNome(contato.getNome());
        dto.setContato(contato.getContato());
        dto.setCreatedDate(contato.getCreatedDate());

        if (contato.getProfissional() != null) {
            dto.setProfissional(contato.getProfissional().getId()); // só o ID agora
        }

        return dto;
    }

    public static Contatos toEntity(ContatoDTO dto) {
        if (dto == null) return null;

        Contatos contato = new Contatos();
        contato.setId(dto.getId());
        contato.setNome(dto.getNome());
        contato.setContato(dto.getContato());
        contato.setCreatedDate(dto.getCreatedDate());

        // profissional vai ser atribuído via serviço, usando o id
        contato.setProfissional(null);

        return contato;
    }
}
