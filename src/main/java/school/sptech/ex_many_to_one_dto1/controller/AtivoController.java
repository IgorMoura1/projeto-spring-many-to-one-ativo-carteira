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
import school.sptech.ex_many_to_one_dto1.exception.NaoEncontradoException;
import school.sptech.ex_many_to_one_dto1.service.AtivoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<AtivoResponseDto>> buscarTodos() {
        List<Ativo> ativos = ativoService.buscarTodos();

        if(ativos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<AtivoResponseDto> listaDto = ativos
                .stream()
                .map(AtivoMapper::toAtivoResponseDto)
                .toList();

        return ResponseEntity.status(200).body(listaDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<AtivoResponseDto> buscarPorId(@PathVariable int id) {
        Ativo ativo = ativoService.buscarPorId(id);

        AtivoResponseDto dto = AtivoMapper.toAtivoResponseDto(ativo);
        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        Ativo ativo = this.ativoService.buscarPorId(id);

        if(ativo == null) {
            return ResponseEntity.notFound().build();
        }

        this.ativoService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/carteiras")
    public ResponseEntity<List<AtivoResponseDto>> buscarAtivosPorCarteiraNome(@RequestParam String nomeInvestidor) {
        List<Ativo> ativos = ativoService.buscarAtivosPorInvestidorNome(nomeInvestidor);

        if (ativos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<AtivoResponseDto> ativoResponseDtos = ativos.stream()
                .map(AtivoMapper::toAtivoResponseDto)
                .collect(Collectors.toList());

        return ResponseEntity.status(200).body(ativoResponseDtos);
    }

    @GetMapping("/carteiras/media")
    public ResponseEntity<Double> buscarMediaAtivosPorCarteiraNome(@RequestParam String nomeInvestidor) {
        try {
            Double mediaAtivos = ativoService.buscarMediaAtivosPorInvestidorNome(nomeInvestidor);
            return ResponseEntity.ok(mediaAtivos);
        } catch (NaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
