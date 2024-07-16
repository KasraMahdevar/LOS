package com.example.bsc.service;

import com.example.bsc.model.Bestellung;
import com.example.bsc.model.BestellungDTOs.BestellungGetDto;
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

    /**
     *
     * @return eine Liste von allem Bestellungen sonst nichts
     */
    public List<BestellungGetDto> getAll(){
        List<Bestellung> alle_bestellungen= bestellungsRepo.findAll();
        List<BestellungGetDto> dto_bestellungen = new ArrayList<>();
        if (alle_bestellungen.isEmpty()){
            throw new RuntimeException("keine Bestellung vorhanden!");
        }
        for(Bestellung bestellung : alle_bestellungen){
            BestellungGetDto bestellungGetDto = new BestellungGetDto();

            bestellungGetDto.setLiefernummer(bestellung.getLiefernummer());
            bestellungGetDto.setLieferDatum(bestellung.getLieferDatum());
            bestellungGetDto.setBesteller_id(bestellung.getBesteller());
            bestellungGetDto.setWarenListe(bestellung.getWarenListe());

            dto_bestellungen.add(bestellungGetDto);

        }
            return dto_bestellungen;
    }


    /**
     *
     * @param bestellung
     */
    @Transactional
    public void addBestellung(Bestellung bestellung){
        Bestellung new_bestellung = new Bestellung();
        new_bestellung.setBesteller(bestellung.getBesteller());
        new_bestellung.setLieferDatum(bestellung.getLieferDatum());
        new_bestellung.setLiefernummer(bestellung.getLiefernummer());
        new_bestellung.setWarenListe(bestellung.getWarenListe());

        bestellungsRepo.save(new_bestellung);
    }

    /**
     *
     * @param id von der gewünschten Bestellung
     */
    @Transactional
    public void deleteBestellung(Long id){
         bestellungsRepo.deleteById(id);
    }

    /**
     *
     * @param id von der gewünschten Bestellung
     * @return die gewünschte Bestellung sonst wirft eine Exception, wenn es nicht vorhanden ist
     */
    public Bestellung findBestellungWithId(Long id){
        return bestellungsRepo.findById(id).orElseThrow(
                ()-> new RuntimeException("die Bestellung mit " + id +" existiert nicht!")
        );
    }

}
