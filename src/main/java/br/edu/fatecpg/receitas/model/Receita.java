package br.edu.fatecpg.receitas.model;

import jakarta.persistence.*;

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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
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

    @Override
    public String toString() {
        return "\nid: " + id +
                "\nName: " + name +
                "\nTempo de preparo: " + tempoPreparoMinutos + "min" +
                "\nTempo de COzimento: " + tempoCozimentoMinutos + "min" +
                "\nPorções: " + quantidadePorcoes +
                "\nDificuldade: " + dificuldade +
                "\nIngredients: " + ingredients;
    }
}
