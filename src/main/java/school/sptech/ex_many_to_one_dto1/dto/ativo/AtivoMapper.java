package school.sptech.ex_many_to_one_dto1.dto.ativo;

import school.sptech.ex_many_to_one_dto1.entity.Ativo;
import school.sptech.ex_many_to_one_dto1.entity.Carteira;

public class AtivoMapper {

    public static AtivoResponseDto toAtivoResponseDto(Ativo ativo) {
        if (ativo == null) return null;

        Carteira carteira = ativo.getCarteira();

        if (carteira == null) return null;

        AtivoResponseDto.AtivoCarteiraResponseDto ativoCarteiraResponseDto =
                AtivoResponseDto.AtivoCarteiraResponseDto
                        .builder()
                        .id(carteira.getId())
                        .nome(carteira.getNome())
                        .investidor(carteira.getInvestidor())
                        .build();

        return AtivoResponseDto
                .builder()
                .id(ativo.getId())
                .nome(ativo.getNome())
                .tipo(ativo.getTipo())
                .valorAtual(ativo.getValorAtual())
                .carteira(ativoCarteiraResponseDto)
                .build();
    }

    public static Ativo toAtivoEntity(AtivoRequestDto dto) {
        if (dto == null) return null;

        Ativo ativo = new Ativo();
        ativo.setNome(dto.getNome());
        ativo.setTipo(dto.getTipo());
        ativo.setValorAtual(dto.getValorAtual());
        return ativo;
    }
}