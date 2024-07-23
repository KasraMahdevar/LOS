package com.example.bsc;

import com.example.bsc.einlagerung.lager.Lager;
import com.example.bsc.einlagerung.lager.Lagerplatz;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class BscApplication {

    public static void main(String[] args) {
        SpringApplication.run(BscApplication.class, args);

        // ein Objekt vom Lager wird erstellt
        lagerErstellen();
    }

    /**
     * Lager erstellen, sobald die App läuft
     * Lager erstellen und die Lagerplätze einfügen
     */
    public static void lagerErstellen() {
        Lager lager = Lager.getInstance();
        int random_height = new Random().nextInt(3, 5);
        int random_width = new Random().nextInt(1, 3);
        for (Long i = 1L; i < 700; i++) {
            Lagerplatz lagerplatz = new Lagerplatz(i, random_height, random_width);
            lager.getLagerplatzs().add(lagerplatz);
        }

    }

}
