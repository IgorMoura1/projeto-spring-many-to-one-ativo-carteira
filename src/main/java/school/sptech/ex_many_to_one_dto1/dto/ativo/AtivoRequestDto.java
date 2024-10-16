package school.sptech.ex_many_to_one_dto1.dto.ativo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtivoRequestDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String tipo;
    @NotNull
    private Double valorAtual;
    @NotNull
    private Integer carteiraId;
}
