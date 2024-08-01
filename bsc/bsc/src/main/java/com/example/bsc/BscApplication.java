package com.example.bsc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bsc.prozesse.entities.Lagerplatz;
import com.example.bsc.prozesse.lagerplatz.Lager;
import com.example.bsc.prozesse.lagerplatz.service.LagerplatzService;
import com.mysql.cj.jdbc.DatabaseMetaData;
import com.example.bsc.los.service.LosService;

@SpringBootApplication
public class BscApplication implements CommandLineRunner {

    @Autowired
    private LagerplatzService lagerplatzService;

    @Autowired
    private LosService losService;


    public static void main(String[] args) {
        get_tables();
        //SpringApplication.run(BscApplication.class, args);

    }

    /**
     * Die Lagerplätze erstellen, sobald die App läuft
     * Die die Lagerplätze in DB speichern
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
        System.out.println("Lagerplätze erfolgreich inizialisiert");
       

    }


    public static void get_tables(){
         // Datenbankverbindungsdetails
         String url = "jdbc:jtds:sqlserver://172.18.109.6/Schoko_GDS_Lager_Transfer";
         String user = "Turki";
         String password = "pMvGDL12wemx97D";
 
         // Verbindung, Metadata und ResultSet
         Connection connection = null;
         ResultSet tables = null;
 
         try {
             // Verbindung zur Datenbank herstellen
             connection = DriverManager.getConnection(url, user, password);
 
             // DatabaseMetaData abrufen
             java.sql.DatabaseMetaData metaData = connection.getMetaData();
 
             // Tabellen abfragen
             tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});
 
             // Tabellenname ausgeben
             while (tables.next()) {
                 String tableName = tables.getString("TABLE_NAME");
                 System.out.println("Table Name: " + tableName);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
             // Ressourcen schließen
             try { if (tables != null) tables.close(); } catch (SQLException e) { e.printStackTrace(); }
             try { if (connection != null) connection.close(); } catch (SQLException e) { e.printStackTrace(); }
         }
     }
 
    }


