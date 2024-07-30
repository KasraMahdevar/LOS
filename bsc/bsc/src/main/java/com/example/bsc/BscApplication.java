package com.example.bsc;

import com.example.bsc.prozesse.lagerplatz.Lager;
import com.example.bsc.prozesse.entities.Lagerplatz;
import com.example.bsc.prozesse.lagerplatz.service.LagerplatzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Random;

@SpringBootApplication
public class BscApplication implements CommandLineRunner {

    @Autowired
    private LagerplatzService lagerplatzService;


    public static void main(String[] args) {
        SpringApplication.run(BscApplication.class, args);

        // ein Objekt vom Lager wird erstellt
    }

    /**
     * Lager erstellen, sobald die App läuft
     * Lager erstellen und die Lagerplätze in DB speichern
     */
    @Override
    public void run(String... args) throws Exception {
        Lager lager = Lager.getInstance();
        int random_height = new Random().nextInt(3, 5);
        int random_width = new Random().nextInt(1, 3);
        for (Long i = 1L; i < 700; i++) {
            Lagerplatz lagerplatz = new Lagerplatz(i, random_height, random_width);
            lagerplatzService.save_lagerplatz_in_db(lagerplatz);
            lager.getLagerplatzs().add(lagerplatz);
        }
        // get some data from los Database

    }
}
