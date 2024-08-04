package com.example.bsc;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bsc.los.service.LosService;
import com.example.bsc.prozesse.entities.Lagerplatz;
import com.example.bsc.prozesse.lagerplatz.Lager;
import com.example.bsc.prozesse.lagerplatz.service.LagerplatzService;

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
           // System.out.println("Lagerplätze wurden erfolgreich erstellt und in PostgreSQL (LocalDB) gespeichert");
        };
    }

     /* @Bean(name = "Test DB-Connection")
    public CommandLineRunner test_connection() {
        return new CommandLineRunner() {
            @Override
            public void run(String[] args) throws Exception {
                Connection connection = null;
                System.out.println("Testing the Connection...");
                try {
                    connection = DriverManager.getConnection(
                            "jdbc:jtds:sqlserver://los.bsc-intern.de/Schoko_GDS_Lager_Transfer;",
                            "Turki",
                            "pMvGDL12wemx97D"
                    );
                    System.out.println("Connected!");
                    DatabaseMetaData metaData = connection.getMetaData();
                    ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
                    
                    // Tabellenname ausgeben
                    while (tables.next()) {
                        String tableName = tables.getString("TABLE_NAME");
                        System.out.println("Table Name: " + tableName);
                    }
                } catch (SQLException msg) {
                    System.out.println("Connection Error: " + msg.getMessage());
                }
            }
        };

    } */

    /**
     *
     * @return eine Liste von Tabellennamen aus LOS RemoteDB
     */
    @Bean(name = "Tabellennamen abrufen")
    public CommandLineRunner get_table_names() {
        return args -> {
            System.out.println("==========Ausgaben==========");
            System.out.println("Anzahl der elemente in der Liste:");
            System.out.println(losService.getTableNames().size());

            for (String tableName : losService.getTableNames()) {
                System.out.println(tableName);
            }

        };
    }

}
 


