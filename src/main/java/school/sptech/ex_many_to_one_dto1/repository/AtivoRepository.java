package school.sptech.ex_many_to_one_dto1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.ex_many_to_one_dto1.entity.Ativo;

public interface AtivoRepository extends JpaRepository<Ativo, Integer> {

}
