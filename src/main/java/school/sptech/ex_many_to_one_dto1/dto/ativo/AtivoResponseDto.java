package school.sptech.ex_many_to_one_dto1.dto.ativo;

import lombok.Builder;
import lombok.Data;

// TODO: TERMINAR A CLASSE
@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class AtivoResponseDto {

    private Integer id;
    private String nome;
    private String tipo;
    private Double valorAtual;
    private AtivoCarteiraResponseDto carteira;

    @Data
    @Builder
//    @NoArgsConstructor
//    @AllArgsConstructor
    public static class AtivoCarteiraResponseDto {
        private Integer id;
        private String nome;
        private String investidor;
    }
}
