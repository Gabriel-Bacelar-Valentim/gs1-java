package br.fiap.com.global_solution_java.repository;

import br.fiap.com.global_solution_java.entity.DadoSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadoSersorRepository extends JpaRepository<DadoSensor, Long> {
}
