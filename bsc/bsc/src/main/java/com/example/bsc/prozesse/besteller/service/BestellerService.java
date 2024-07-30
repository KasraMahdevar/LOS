package com.example.bsc.prozesse.besteller.service;

import java.util.ArrayList;
import java.util.List;

import com.example.bsc.prozesse.entities.Besteller;
import com.example.bsc.prozesse.besteller.repo.BestellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bsc.prozesse.besteller.BestellerDTOs.BestellerGetDto;
import com.example.bsc.prozesse.besteller.BestellerDTOs.BestellerPostDto;

import jakarta.transaction.Transactional;


@Service
public class BestellerService {

    @Autowired
    private BestellerRepo bestellerRepo;


    /**
     * 
     * @return gibt alle Personal im System zurück sonst msgError
     */
    public List<BestellerGetDto> getAll(){
        List<Besteller> bestellerListe =  bestellerRepo.findAll();
        if(bestellerListe.isEmpty()){
            throw new RuntimeException("Kein Personal im System vorhanden!");
        }
        List<BestellerGetDto> dtos = new ArrayList<>();
        for(Besteller besteller : bestellerListe){
            BestellerGetDto dto = new BestellerGetDto();
            dto.setId(besteller.getId());
            dto.setVorname(besteller.getVorname());
            dto.setNachname(besteller.getNachname());
            dtos.add(dto);
            
        }
        return dtos;
    }

    /**
     * 
     * @param id von Besteller
     * @return gibt den Besteller mit der angegebenen ID zurück sonst msgError
     */
    public BestellerGetDto getBestellerWithId(Long id){
        Besteller besteller = bestellerRepo.findById(id).orElseThrow(
            () -> new RuntimeException("Kein Personal mit  " + id + " im System vorhanden")
        );
        BestellerGetDto dto = new BestellerGetDto();
        dto.setId(besteller.getId());
        dto.setVorname(besteller.getVorname());
        dto.setNachname(besteller.getNachname());
        return dto;
    }

    /**
     * fügt einen neuen Besteller im System
     * @param bestellerPostDto
     */
    @Transactional
    public void addBesteller(BestellerPostDto bestellerPostDto){
            Besteller besteller = new Besteller();
            besteller.setVorname(bestellerPostDto.getVorname());
            besteller.setNachname(bestellerPostDto.getNachname());
            bestellerRepo.save(besteller);    
    }

    /**
     * löscht den Besteller mit der angegebenen ID vom System, wenn es vorhanden ist sonst msgError
     * @param id von Besteller
     */
    @Transactional
    public void deleteBestellerWithId(Long id){
        bestellerRepo.findById(id).orElseThrow(
            () -> new RuntimeException("Kein Personal mit " + id + " im Systen gefunden!")
        );

    }




}
