package com.example.bsc.service;

import com.example.bsc.einlagerung.einlagerungsprozess.Abholetikett;
import com.example.bsc.model.Bestellung;
import com.example.bsc.model.BestellungDTOs.BestellungGetDto;
import com.example.bsc.model.BestellungDTOs.BestellungPostDto;
import com.example.bsc.model.Ware;
import com.example.bsc.model.WareDTOs.WarePostDto;
import com.example.bsc.personal.Besteller;
import com.example.bsc.repo.BestellerRepo;
import com.example.bsc.repo.BestellungsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BestellungsService {

    @Autowired
    private BestellungsRepo bestellungsRepo;

    @Autowired
    private BestellerRepo bestellerRepo;

    @Autowired
    private WareService wareService;

    @Autowired
    private AbholetikettService abholetikettService;

    /**
     * @return eine Liste von allem Bestellungen sonst nichts
     */
    public List<BestellungGetDto> getAll() {
        List<Bestellung> alle_bestellungen = bestellungsRepo.findAll();
        List<BestellungGetDto> dto_bestellungen = new ArrayList<>();
        if (alle_bestellungen.isEmpty()) {
            throw new RuntimeException("keine Bestellung vorhanden!");
        }
        for (Bestellung bestellung : alle_bestellungen) {
            BestellungGetDto bestellungGetDto = new BestellungGetDto();

            bestellungGetDto.setLiefernummer(bestellung.getLiefernummer());
            bestellungGetDto.setLieferDatum(bestellung.getLieferDatum());
            bestellungGetDto.setBesteller(bestellung.getBesteller());
            bestellungGetDto.setWarenListe(bestellung.getWarenListe());
            bestellungGetDto.setFreigabe(bestellung.getFreigabe());

            dto_bestellungen.add(bestellungGetDto);

        }
        return dto_bestellungen;
    }


    /**
     * @param bestellungPostDto
     */
    @Transactional
    public void addBestellung(BestellungPostDto bestellungPostDto) {
        Besteller besteller = bestellerRepo.findById(bestellungPostDto.getBesteller_id()).orElseThrow(
                () -> new RuntimeException("Kein Personal mit der ID " + bestellungPostDto.getBesteller_id() + " im System vorhanden!")
        );

        Bestellung new_bestellung = new Bestellung();
        new_bestellung.setBesteller(besteller);
        new_bestellung.setLieferDatum(bestellungPostDto.getLieferDatum());
        new_bestellung.setLiefernummer(bestellungPostDto.getLiefernummer());
        for (WarePostDto wareDto : bestellungPostDto.getWarenListe()) {
            Ware neue_ware = wareService.addWare(wareDto);
            /* Bestellung sollte eingesetzt werden sonst die Liste von Waren werden nicht zurückgegeben */
            neue_ware.setBestellung(new_bestellung);
            new_bestellung.getWarenListe().add(neue_ware);
        }

        bestellungsRepo.save(new_bestellung);
    }

    /**
     * @param id von der gewünschten Bestellung
     */
    @Transactional
    public void deleteBestellung(Long id) {
        bestellungsRepo.deleteById(id);
    }

    /**
     * @param id von der gewünschten Bestellung
     * @return die gewünschte Bestellung sonst wirft eine Exception, wenn es nicht vorhanden ist
     */
    public Bestellung findBestellungWithId(Long id) {
        return bestellungsRepo.findById(id).orElseThrow(
                () -> new RuntimeException("die Bestellung mit " + id + " existiert nicht!")
        );
    }

    /**
     * @param liefernummer, mit der eine Bestellung zur Einlagerung bestätigt wird.
     *                      für jede bestätigte Bestellung sollte ein Abholetikett für den Stapler ausgestellt werden.
     */
    @Transactional
    public void bestellungFreigeben(Long liefernummer) {
        Bestellung bestellung = bestellungsRepo.findBestellungByLiefernummer(liefernummer);
        if (bestellung != null) {
            Abholetikett abholetikett = new Abholetikett(bestellung.getWarenListe());

            bestellung.setFreigabe(true);
            bestellungsRepo.save(bestellung);

            abholetikettService.getAbholetikettRepo().save(abholetikett);
        } else
            throw new RuntimeException("Keine Betsellung mit dieser Lieferungsnummer vorhanden");
    }


}
