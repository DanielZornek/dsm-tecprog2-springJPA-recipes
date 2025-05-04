package br.edu.fatecpg.receitas.repository;

import br.edu.fatecpg.receitas.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
}
