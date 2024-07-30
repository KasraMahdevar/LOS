package com.example.bsc.prozesse.bestellung.contoller;

import com.example.bsc.prozesse.entities.Bestellung;
import com.example.bsc.prozesse.bestellung.BestellungDTOs.BestellungGetDto;
import com.example.bsc.prozesse.bestellung.BestellungDTOs.BestellungPostDto;
import com.example.bsc.prozesse.bestellung.service.BestellungsService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/bestellungen")
public class BestellungsController {

    @Autowired
    private BestellungsService bestellungsService;

    @Tag(name = "Alle Bestellungen")
    @GetMapping("/get_all")
    public List<BestellungGetDto> getAll() {
        return bestellungsService.getAll();
    }


    @Tag(name = "Eine neue Bestellung einfügen")
    @PostMapping("/add_bestellung")
    public void addBestellung(@Parameter(required = true) @RequestBody BestellungPostDto bestellungPostDto) {
        bestellungsService.addBestellung(bestellungPostDto);
    }

    @Tag(name = "Eine Bestellung mit ID nehmen")
    @GetMapping("/{id}")
    public Bestellung getWithId(@Parameter(required = true) @PathVariable Long id) {
        return bestellungsService.findBestellungWithId(id);
    }


    @Tag(name = "Alle Bestellungen, die heute eingeliefert werden sollen")
    @GetMapping("/heute")
    public List<BestellungGetDto> getAllBestellungenHeute() {
        return bestellungsService.getAll().stream().filter(bestellung -> LocalDate.now().isEqual(
                bestellung.getLieferDatum()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate())).collect(Collectors.toList());

    }

    @Tag(name = "Alle Bestellungen im bestimmen Datum")
    @GetMapping("/date/{datum}")
    public List<BestellungGetDto> getBestellungenWithDate(@Parameter(required = true) @PathVariable String datum) {

        System.out.println(datum);
        System.out.println("====================AUSGABE======================");

        int tag = Integer.parseInt(datum.split("-")[0]);
        int monat = Integer.parseInt(datum.split("-")[1]);
        int jahr = Integer.parseInt(datum.split("-")[2]);
        LocalDate date = LocalDate.of(jahr, monat,tag);
        System.out.println(date);
        return bestellungsService.getAll().stream().filter(
                bestellungen -> date.isEqual(
                        bestellungen.getLieferDatum()
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                )
        ).collect(Collectors.toList());
    }

    @Tag(name="Eine bestellung freigeben")
    @PutMapping("/freigabe/{liefernummer}")
    public void bestellungFreigeben(@Parameter(required = true) @PathVariable Long liefernummer){
        bestellungsService.bestellungFreigeben(liefernummer);
    }


}
