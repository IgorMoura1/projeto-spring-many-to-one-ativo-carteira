package school.sptech.ex_many_to_one_dto1.dto.ativo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtivoRequestDto {

    @NotBlank(message = "O nome não pode estar vazio.")
    private String nome;

    @NotBlank(message = "O tipo não pode estar vazio.")
    private String tipo;

    @NotNull(message = "O valor atual não pode ser nulo.")
    @Positive(message = "O valor atual deve ser positivo.")
    private Double valorAtual;

    @NotNull(message = "O ID da carteira não pode ser nulo.")
    @Min(value = 1, message = "O ID da carteira deve ser um número positivo.")
    private Integer carteiraId;
}
