package school.sptech.ex_many_to_one_dto1.dto.carteira;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarteiraResponseDto {
    private int id;
    private String nome;
    private String investidor;
}
