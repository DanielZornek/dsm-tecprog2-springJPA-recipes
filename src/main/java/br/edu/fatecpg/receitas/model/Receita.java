package br.edu.fatecpg.receitas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import java.util.List;

@Entity
@Table(name="Receita")
public class Receita {
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "tempo_preparo_minutos")
    private Integer tempoPreparoMinutos;
    @Column(name = "tempo_cozimento_minutos")
    private Integer tempoCozimentoMinutos;
    @Column(name = "quantidade_porcoes")
    private Integer quantidadePorcoes;
    @Column(name = "dificuldade")
    private String dificuldade;
    @ElementCollection @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;
}
