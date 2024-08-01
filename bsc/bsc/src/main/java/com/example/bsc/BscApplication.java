package com.example.bsc;

import com.example.bsc.los.service.LosService;
import com.example.bsc.prozesse.entities.Lagerplatz;
import com.example.bsc.prozesse.lagerplatz.Lager;
import com.example.bsc.prozesse.lagerplatz.service.LagerplatzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class BscApplication {

    @Autowired
    private LagerplatzService lagerplatzService;

    @Autowired
    private LosService losService;


    public static void main(String[] args) {
        SpringApplication.run(BscApplication.class, args);

    }

    /**
     * initialisiert die Lagerplätze in PostgreSQL (LocalDB)
     * @return none
     */
    @Bean("Lagerplatz erstellen")
    public CommandLineRunner lagerplatz_erstellen() {
        return args -> {
            Lager lager = Lager.getInstance();
            int random_height = new Random().nextInt(3, 5);
            int random_width = new Random().nextInt(1, 3);
            for (long i = 1L; i < 700; i++) {
                Lagerplatz lagerplatz = new Lagerplatz(i, random_height, random_width);
                lagerplatzService.save_lagerplatz_in_db(lagerplatz);
                lager.getLagerplatzs().add(lagerplatz);
            }
            System.out.println("Lagerplätze wurden erfolgreich erstellt und in PostgreSQL (LocalDB) gespeichert");
        };
    }

    /**
     *
     * @return eine Liste von Tabellennamen aus LOS RemoteDB
     */
    @Bean(name = "Tabellennamen abrufen")
    public CommandLineRunner get_table_names() {
        return args -> {
            System.out.println("==========Tabellen Namen==========");
            for (String tableName : losService.getTableNames()) {
                System.out.println(tableName);
            }

        };
    }

}
 


