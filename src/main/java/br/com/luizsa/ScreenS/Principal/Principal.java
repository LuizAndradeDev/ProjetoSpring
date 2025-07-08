package br.com.luizsa.ScreenS.Principal;

import br.com.luizsa.ScreenS.model.DadosEpisodio;
import br.com.luizsa.ScreenS.model.DadosSerie;
import br.com.luizsa.ScreenS.model.DadosTemporada;
import br.com.luizsa.ScreenS.model.Episodio;
import br.com.luizsa.ScreenS.service.ConsumoApi;
import br.com.luizsa.ScreenS.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private ConverteDados conversor = new ConverteDados();
    private Scanner scanner = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private ConsumoApi consumo = new ConsumoApi();

    public void exibeMenu() {
        System.out.println("Digite o nome da serie para busca");
        var nomeSerie = scanner.nextLine();

        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);

        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i < dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);

   temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

//        List<String> nomes = Arrays.asList("João", "Paulo", "Guilherme", "Zé");
//
//        nomes.stream()
//                .sorted()
//                .limit(3)
//                .filter(n -> n.startsWith("N"))
//                .map(n -> n.toUpperCase())
//                .forEach(System.out::println);

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episodios:");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toUnmodifiableList());

        episodios.forEach(System.out::println);


    }
}
