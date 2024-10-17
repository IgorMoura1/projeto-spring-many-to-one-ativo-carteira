package school.sptech.ex_many_to_one_dto1.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;
import school.sptech.ex_many_to_one_dto1.entity.Carteira;
import school.sptech.ex_many_to_one_dto1.exception.NaoEncontradoException;
import school.sptech.ex_many_to_one_dto1.repository.AtivoRepository;

import java.util.List;

// TODO: TERMINAR A CLASSE
@Service
@RequiredArgsConstructor
public class AtivoService {

    private final AtivoRepository ativoRepository;
    private final CarteiraService carteiraService;


    public Ativo salvar(Ativo ativoEntidade, @NotNull int carteiraId) {
        Carteira carteiraEncontrada = carteiraService.buscarPorId(carteiraId);

        ativoEntidade.setCarteira(carteiraEncontrada);
        return ativoRepository.save(ativoEntidade);
    }

    public List<Ativo> buscarTodos() {
        return ativoRepository.findAll();
    }

    public Ativo buscarPorId(int id) {
        return ativoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Ativo de id: " + id + " não encontrado"));
    }

    public void deletarPorId(int id) {
        Boolean ativo = ativoRepository.existsById(id);

        if (!ativo) {
            throw new NaoEncontradoException("Ativo de id: " + id + " não encontrado");
        } else {
            ativoRepository.deleteById(id);
        }
    }

    public List<Ativo> buscarAtivosPorInvestidorNome(String investidor) {
        return ativoRepository.findByCarteiraInvestidorContainingIgnoreCase(investidor);
    }


    public Double buscarMediaAtivosPorInvestidorNome(String nome) {
        Double mediaAtivos = ativoRepository.buscarMediaAtivosPorInvestidorNome(nome);

        if (mediaAtivos == null) {
            throw new NaoEncontradoException("Não foram encontrados ativos para o investidor com nome: " + nome);
        }

        return mediaAtivos;
    }
}
