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
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: TERMINAR A CLASSE
@RestController
@RequestMapping("/ativos")
@RequiredArgsConstructor
public class AtivoController {

    private final AtivoService ativoService;

    @PostMapping
    public ResponseEntity<AtivoResponseDto> salvar(
            @RequestBody @Valid AtivoRequestDto ativoRequestDto) {
        Ativo ativoEntidade = AtivoMapper.toAtivoEntity(ativoRequestDto);

        Ativo ativoSalvoNoBanco = ativoService.salvar(ativoEntidade, ativoRequestDto.getCarteiraId());

        AtivoResponseDto ativoResponseDto = AtivoMapper.toAtivoResponseDto(ativoSalvoNoBanco);
        return ResponseEntity.created(null).body(ativoResponseDto);
    }

    public ResponseEntity<List<AtivoResponseDto>> buscarTodos() {
        List<Ativo> ativos = ativoService.buscarTodos();

        if(ativos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AtivoResponseDto> listaDto = ativos
                .stream()
                .map(AtivoMapper::toAtivoResponseDto)
                .toList();

        return ResponseEntity.status(200).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AtivoResponseDto> buscarPorId(@PathVariable int id) {
        Ativo ativo = this.ativoService.buscarPorId(id);

        AtivoResponseDto dto = AtivoMapper.toAtivoResponseDto(ativo);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        Ativo ativo = this.ativoService.buscarPorId(id);

        if(ativo == null) {
            return ResponseEntity.notFound().build();
        }

        this.ativoService.deletarPorId(id);

        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<List<AtivoResponseDto>> buscarAtivosPorCarteiraNome(String nomeInvestidor) {
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<Double> buscarMediaAtivosPorCarteiraNome(String nomeInvestidor) {
        return ResponseEntity.internalServerError().build();
    }
}
