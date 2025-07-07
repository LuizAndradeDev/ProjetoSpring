package br.com.luizsa.ScreenS.Principal;

import br.com.luizsa.ScreenS.model.DadosSerie;
import br.com.luizsa.ScreenS.model.DadosTemporada;
import br.com.luizsa.ScreenS.service.ConsumoAPI;
import br.com.luizsa.ScreenS.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private ConverteDados conversor = new ConverteDados();
    private Scanner scanner = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private ConsumoAPI consumo = new ConsumoAPI();

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

    }
}
