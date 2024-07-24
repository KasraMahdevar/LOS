package com.example.bsc.controller;

import com.example.bsc.einlagerung.einlagerungsprozess.Abholetikett;
import com.example.bsc.einlagerung.einlagerungsprozess.AbholetikettGetDto;
import com.example.bsc.einlagerung.einlagerungsprozess.Status;
import com.example.bsc.einlagerung.lager.Lagerplatz;
import com.example.bsc.model.Ware;
import com.example.bsc.personal.Stapler;
import com.example.bsc.personal.StaplerDTOs.StaplerGetDto;
import com.example.bsc.personal.StaplerDTOs.StaplerPostDto;
import com.example.bsc.service.AbholetikettService;
import com.example.bsc.service.LagerplatzService;
import com.example.bsc.service.StaplerService;
import com.example.bsc.service.WareService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stapler")
public class StaplerController {

    @Autowired
    private StaplerService staplerService;

    @Autowired
    private AbholetikettService abholetikettService;

    @Autowired
    private LagerplatzService lagerplatzService;

    @Autowired
    private WareService wareService;

    @Tag(name = "findet alle Stapler im System")
    @GetMapping("/get_all")
    public List<StaplerGetDto> get_all() {
        return staplerService.getAll();
    }

    @Tag(name = "Nimmt einen Stapler mit ID")
    @GetMapping("/{id}")
    public StaplerGetDto getStaplerMitId(@Parameter(required = true) @PathVariable Long id) {
        return staplerService.getSpatlerWithId(id);
    }

    @Tag(name = "Fügt einen Stapler im System ein")
    @PostMapping("/add_stapler")
    public void add_stapler(@Parameter(required = true) @RequestBody StaplerPostDto staplerPostDto) {
        staplerService.addStapler(staplerPostDto);
    }

    @Tag(name = "Abholbereite Waren für die Einlagerung durch den Stapler")
    @GetMapping("/abholbereite_waren")
    public List<AbholetikettGetDto> abholbereiteWaren() {
        return abholetikettService.abholbereite_waren();
    }

    @Tag(name = "Ware abholen")
    @GetMapping("/ware_abholen/{stapler_id}/{abhl_etk_nummer}")
    public void waren_abholen(@Parameter(required = true) @PathVariable Long stapler_id, @Parameter(required = true) @PathVariable Long abhl_etk_nummer) {
        Abholetikett abhol_etk = abholetikettService.getAbholetikettRepo().findAbholetikettByEtk_nummer(abhl_etk_nummer);
        Stapler stapler = staplerService.getStaplerRepo().findById(stapler_id).get();
        if (abhol_etk == null) {
            throw new RuntimeException("Keine Etikette mit dieser Nummer vorhanden!");
        }
        abhol_etk.setStapler(stapler);
        abhol_etk.setStatus(Status.IN_BEARBEITUNG);

        abholetikettService.getAbholetikettRepo().save(abhol_etk);

    }

    @Tag(name = "Einlagerung bestätigen")
    @GetMapping("/einlagerung/{abhl_etk_nummer}")
    public void einlagerung(@Parameter(required = true) @PathVariable Long abhl_etk_nummer) {
        Abholetikett abhol_etk = abholetikettService.getAbholetikettRepo().findAbholetikettByEtk_nummer(abhl_etk_nummer);
        if (abhol_etk == null) {
            throw new RuntimeException("Keine Etikette mit dieser Nummer vorhanden!");
        }
        //TODO Lagerplatz sollte verglichen werden, bevor die Einlagerung erfolgt sonst Error msg für den Staplerfahrer
        abhol_etk.setStatus(Status.DONE);
        abholetikettService.getAbholetikettRepo().save(abhol_etk);

        //Die Waren beim Lagerplatz müssen aktualisiert werden
        System.out.println("gefundene Platznummer vom Etikett: " + abhol_etk.getLagerplatznummer());
        Lagerplatz lagerplatz = lagerplatzService.getLagerplatzRepo().findLagerplatzByPlatznummer(abhol_etk.getLagerplatznummer());
        if (lagerplatz == null) {
            throw new RuntimeException("Lagerplatz nicht vorhanden zum aktualisieren!");
        }
        System.out.println("Lagerplatz wurde gefunden: " + lagerplatz.getPlatznummer());
        System.out.println("Waren werden zum Lagerplatz eingefügt ...");
        List<Ware> warenList = new ArrayList<>();
        for(Ware ware : abhol_etk.getWarenList()){
            ware.setLagerplatz(lagerplatz);
            wareService.getWareRepo().save(ware);
            warenList.add(ware);
        }
        lagerplatz.setWarenListe(warenList);
        System.out.println("Waren wurden erfolgreich zum Lagerplatz eingefügt ...");
        lagerplatz.setBesetzt(true);
        lagerplatzService.save_lagerplatz_in_db(lagerplatz);
        System.out.println("lagerplatz Datenbank wurde aktualisiert!");
        System.out.println("SUPER, Einlagerung DONE");

    }


    @Tag(name = "Löscht einen Stapler aus dem System")
    @DeleteMapping("/delete_stapler/{id}")
    public void delete_stapler(@Parameter(required = true) @PathVariable Long id) {
        staplerService.deleteSpatler(id);
    }
}




















