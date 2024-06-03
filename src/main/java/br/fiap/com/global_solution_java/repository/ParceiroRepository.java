package br.fiap.com.global_solution_java.repository;


import br.fiap.com.global_solution_java.entity.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
}
