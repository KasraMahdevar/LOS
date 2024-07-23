package com.example.bsc.controller;

import com.example.bsc.einlagerung.einlagerungsprozess.Abholetikett;
import com.example.bsc.einlagerung.einlagerungsprozess.AbholetikettGetDto;
import com.example.bsc.einlagerung.einlagerungsprozess.Status;
import com.example.bsc.einlagerung.lager.Lagerplatz;
import com.example.bsc.personal.Stapler;
import com.example.bsc.personal.StaplerDTOs.StaplerGetDto;
import com.example.bsc.personal.StaplerDTOs.StaplerPostDto;
import com.example.bsc.service.AbholetikettService;
import com.example.bsc.service.LagerplatzService;
import com.example.bsc.service.StaplerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        abhol_etk.setStatus(Status.DONE);
        abholetikettService.getAbholetikettRepo().save(abhol_etk);

        //Die Waren beim Lagerplatz müssen aktualisiert werden
        Lagerplatz lagerplatz = lagerplatzService.getLagerplatzRepo().findLagerplatzByPlatznummer(abhol_etk.getLagerplatznummer());
        if (lagerplatz == null) {
            throw new RuntimeException("Lagerplatz nicht vorhanden zum aktualisieren!");
        }
        lagerplatz.setWarenListe(abhol_etk.getWarenList());
        lagerplatzService.getLagerplatzRepo().save(lagerplatz);

    }


    @Tag(name = "Löscht einen Stapler aus dem System")
    @DeleteMapping("/delete_stapler/{id}")
    public void delete_stapler(@Parameter(required = true) @PathVariable Long id) {
        staplerService.deleteSpatler(id);
    }
}




















