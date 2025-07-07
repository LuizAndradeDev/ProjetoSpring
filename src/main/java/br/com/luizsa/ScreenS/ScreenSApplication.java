package br.com.luizsa.ScreenS;

import br.com.luizsa.ScreenS.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenSApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenSApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibeMenu();
    }

}
