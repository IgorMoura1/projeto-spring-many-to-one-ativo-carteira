package school.sptech.ex_many_to_one_dto1.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;
import school.sptech.ex_many_to_one_dto1.entity.Carteira;
import school.sptech.ex_many_to_one_dto1.exception.NaoEncontradoException;
import school.sptech.ex_many_to_one_dto1.repository.AtivoRepository;

import java.util.List;

// TODO: TERMINAR A CLASSE
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;
import school.sptech.ex_many_to_one_dto1.repository.AtivoRepository;

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
        Ativo ativo = buscarPorId(id);

        ativoRepository.delete(ativo);
    }

    public List<Ativo> buscarAtivosPorInvestidorNome(String nome) {
        return ativoRepository.findByCarteiraInvestidorIgnoreCase(nome);
    }

    public Double buscarMediaAtivosPorInvestidorNome(String nome) {
        return ativoRepository.buscarMediaAtivosPorInvestidorNome(nome);
    }
}
