package br.com.luizsa.ScreenS.Principal;

import br.com.luizsa.ScreenS.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private ConsumoAPI consumo = new ConsumoAPI();

    public void exibeMenu(){
        System.out.println("Digite o nome da serie para busca");
        var nomeSerie = scanner.nextLine();

        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);



        //""https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=6585022c"gilmore+girls&season=" + i + "&apikey=6585022c"
    }
}
