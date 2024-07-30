package com.example.bsc.prozesse.stapler.service;

import com.example.bsc.prozesse.stapler.StaplerDTOs.StaplerGetDto;
import com.example.bsc.prozesse.stapler.StaplerDTOs.StaplerPostDto;
import com.example.bsc.prozesse.entities.Stapler;
import com.example.bsc.prozesse.stapler.repo.StaplerRepo;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
public class StaplerService {


    @Autowired
    private StaplerRepo staplerRepo;


    /**
     * @return eine Liste von Stapler
     */
    public List<StaplerGetDto> getAll() {
        List<StaplerGetDto> staplerGetDtos = new ArrayList<>();
        List<Stapler> staplers = staplerRepo.findAll();
        if (staplers.isEmpty()) {
            throw new RuntimeException("Keine Stapler vorhanden!");
        }
        for (Stapler stapler : staplers) {
            StaplerGetDto staplerGetDto = new StaplerGetDto();

            staplerGetDto.setId(stapler.getId());
            staplerGetDto.setVorname(stapler.getVorname());
            staplerGetDto.setNachname(stapler.getNachname());

            staplerGetDtos.add(staplerGetDto);
        }
        return staplerGetDtos;

    }

    /**
     * @param staplerPostDto fügt einen neuen Stapler
     */
    @Transactional
    public void addStapler(StaplerPostDto staplerPostDto) {
        Stapler stapler = new Stapler();
        stapler.setVorname(staplerPostDto.getVorname());
        stapler.setNachname(staplerPostDto.getNachname());

        staplerRepo.save(stapler);

    }

    /**
     * @param id von Stapler
     * @return ein Stapler mit der gewünschten ID
     */
    public StaplerGetDto getSpatlerWithId(Long id) {
        Optional<Stapler> stapler = staplerRepo.findById(id);
        if (stapler.isEmpty()) {
            throw new RuntimeException("Stapler nicht gefunden!");
        }
        StaplerGetDto staplerGetDto = new StaplerGetDto();
        staplerGetDto.setNachname(stapler.get().getVorname());
        staplerGetDto.setNachname(stapler.get().getNachname());
        return staplerGetDto;
    }


    /**
     * @param id
     */

    @Transactional
    public void deleteSpatler(Long id) {
        staplerRepo.deleteById(id);
    }
}
