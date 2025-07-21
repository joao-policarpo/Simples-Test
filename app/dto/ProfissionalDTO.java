package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import models.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados do profissional")
public class ProfissionalDTO {

    @Schema(description = "ID do profissional", example = "101")
    private Long id;

    @Schema(description = "Nome completo do profissional", example = "João Silva")
    private String nome;

    @Schema(description = "Cargo exercido pelo profissional", example = "DESENVOLVEDOR")
    private Cargo cargo;

    @Schema(description = "Data de nascimento", example = "1990-05-12")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate nascimento;

    @Schema(description = "Data de criação do cadastro", example = "2025-07-15")
    private LocalDate createdDate;

    @Schema(description = "Lista de contatos associados ao profissional")
    private List<ContatoDTO> contatos;
}
