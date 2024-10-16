package school.sptech.ex_many_to_one_dto1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.ex_many_to_one_dto1.dto.ativo.AtivoMapper;
import school.sptech.ex_many_to_one_dto1.dto.ativo.AtivoRequestDto;
import school.sptech.ex_many_to_one_dto1.dto.ativo.AtivoResponseDto;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;
import school.sptech.ex_many_to_one_dto1.service.AtivoService;

import java.util.ArrayList;
import java.util.List;

// TODO: TERMINAR A CLASSE
@RestController
@RequestMapping("/ativos")
@RequiredArgsConstructor
public class AtivoController {

    private final AtivoService ativoService;


    public ResponseEntity<AtivoResponseDto> cadastrar(
            @RequestBody @Valid AtivoRequestDto ativoRequestDto) {
        Ativo ativoEntidade = AtivoMapper.toAtivoEntity(ativoRequestDto);

        Ativo ativoSalvoNoBanco = ativoService.criar(ativoEntidade, ativoRequestDto.getCarteiraId());

        AtivoResponseDto ativoResponseDto = AtivoMapper.toAtivoResponseDto(ativoSalvoNoBanco);
        return ResponseEntity.created(null).body(ativoResponseDto);
    }

    public ResponseEntity<List<AtivoResponseDto>> buscarTodos() {
        List<Ativo> ativos = ativoService.buscarAtivos();

        if(ativos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AtivoResponseDto> listaDto = new ArrayList<>();

        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<AtivoResponseDto> buscarPorId(Integer id) {
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Void> deletarPorId(Integer id) {
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<List<AtivoResponseDto>> buscarAtivosPorCarteiraNome(String nomeInvestidor) {
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Double> buscarMediaAtivosPorCarteiraNome(String nomeInvestidor) {
        return ResponseEntity.internalServerError().build();
    }
}
