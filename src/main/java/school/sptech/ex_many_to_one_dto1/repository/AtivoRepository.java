package school.sptech.ex_many_to_one_dto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;

import java.util.List;

// TODO: TERMINAR A CLASSE
public interface AtivoRepository extends JpaRepository<Ativo, Integer> {

    public List<Ativo> findByCarteiraInvestidorIgnoreCase(String investidor);

    @Query("SELECT AVG(a.valorAtual) FROM Ativo a WHERE a.carteira.investidor = :nome")
    Double buscarMediaAtivosPorInvestidorNome(@Param("nome") String nome);

}
