package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representação de um contato vinculado a um profissional")
public class ContatoDTO {

    @Schema(description = "ID do contato", example = "1")
    private Long id;

    @Schema(description = "Nome do contato", example = "Celular pessoal")
    private String nome;

    @Schema(description = "Informação de contato (e.g. número ou email)", example = "11988887777")
    private String contato;

    @Schema(description = "Data de criação do contato", example = "2025-07-15")
    private LocalDate createdDate;

    @Schema(description = "ID do profissional associado", example = "42")
    private Long profissional;
}
