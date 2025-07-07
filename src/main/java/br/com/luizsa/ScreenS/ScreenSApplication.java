package br.com.luizsa.ScreenS;

import br.com.luizsa.ScreenS.model.DadosEpisodio;
import br.com.luizsa.ScreenS.model.DadosSerie;
import br.com.luizsa.ScreenS.service.ConsumoAPI;
import br.com.luizsa.ScreenS.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenSApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenSApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		var consumo = new ConsumoAPI();
		var json = consumo.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
//		var json2 = consumo.obterDados("https://coffee.alexflipnote.dev/random.json");
//		System.out.println(json2);
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

		var jsonEpisodio = consumo.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6585022c");
		DadosEpisodio  dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);

		System.out.println(dados);

		System.out.println(dadosEpisodio);

	}

}
