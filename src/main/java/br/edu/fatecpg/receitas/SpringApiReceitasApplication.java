package br.edu.fatecpg.receitas;

import br.edu.fatecpg.receitas.model.Receita;
import br.edu.fatecpg.receitas.model.RecipeDTO;
import br.edu.fatecpg.receitas.repository.ReceitaRepository;
import br.edu.fatecpg.receitas.service.ConsumoAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class SpringApiReceitasApplication implements CommandLineRunner {

	@Autowired
		ReceitaRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(SpringApiReceitasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner entrada = new Scanner(System.in);
		int opcao;
		boolean loop = true;

		System.out.print("Informe o id da receita: ");
		Integer id = entrada.nextInt();
		String receitaJSON = ConsumoAPI.consultarReceita(id);

		System.out.println("Receita: "+receitaJSON);

		try{
			RecipeDTO receita = deserializar(receitaJSON);
			System.out.println(receita);

			Receita receita1 = new Receita(receita.getId(), receita.getName(), receita.getPrepTimeMinutes(), receita.getCookTimeMinutes(), receita.getServings(), receita.getDifficulty(), receita.getIngredients());

			repositorio.save(receita1);
			
		}catch(IOException e){
			System.out.println("Erro ao buscar receita: "+e);
		}
	}

	protected static RecipeDTO deserializar(String json) throws JsonProcessingException {
		ObjectMapper objm = new ObjectMapper();

		return objm.readValue(json, RecipeDTO.class);
	}
}
