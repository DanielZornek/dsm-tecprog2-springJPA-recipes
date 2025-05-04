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

    public Receita(){

    }

    public Receita(Integer id, String name, Integer prepTimeMinutes, Integer cookTimeMinutes, Integer servings, String difficulty, List<String> ingredients) {
        this.id = id;
        this.name = name;
        this.tempoPreparoMinutos = prepTimeMinutes;
        this.tempoCozimentoMinutos = cookTimeMinutes;
        this.quantidadePorcoes = servings;
        this.dificuldade = difficulty;
        this.ingredients = ingredients;
    }
}
