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
import java.util.List;
import java.util.Locale;
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

		while(loop){
			try {
				System.out.println("O que deseja fazer: \n0 - Sair\n1 - Pesquisar Receita\n2 - Listar receitas salvas");
				opcao = entrada.nextInt();

				if (opcao == 0) {
					System.out.println("Fechando programa!");
					loop = false;
				} else if (opcao == 1) {
					System.out.print("Informe o id da receita: ");
					Integer id = entrada.nextInt();
					String receitaJSON = ConsumoAPI.consultarReceita(id);

					entrada.nextLine();

					String escolha = "";
					RecipeDTO receita = deserializar(receitaJSON);
					System.out.println("Comida: " + receita.getName() + "\nDificuldade: " + receita.getDifficulty() +
							"\nDeseja salvar essa receita? y/n: ");
					escolha = entrada.nextLine();

					if (escolha.toLowerCase().equals("y")) {
						Receita receita1 = new Receita(receita.getId(), receita.getName(), receita.getPrepTimeMinutes(), receita.getCookTimeMinutes(), receita.getServings(), receita.getDifficulty(), receita.getIngredients());
						repositorio.save(receita1);
						System.out.println("Receita salva no banco de dados com sucesso!");
					} else if (escolha.toLowerCase().equals("n")) {
						System.out.println("Ok receita ignorada, que tal ver outra receita?");
					} else {
						System.out.println("Desculpe não entendi, tente novamente");
					}
				} else if (opcao == 2) {
					System.out.println("\nAqui estão suas receitas salvas\n");
					List<Receita> receitaList = repositorio.findAll();
					receitaList.forEach(System.out::println);
				} else {
					System.out.println("Opção inválida!");
				}
			}catch(IOException e){
				System.out.println("Cuidado com as entradas de dados!");
			}
		}

		entrada.close();
	}

	protected static RecipeDTO deserializar(String json) throws JsonProcessingException {
		ObjectMapper objm = new ObjectMapper();

		return objm.readValue(json, RecipeDTO.class);
	}
}
