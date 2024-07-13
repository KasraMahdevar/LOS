package com.example.bsc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bsc.model.Ware;
import com.example.bsc.model.WareDTOs.WareGetDto;
import com.example.bsc.model.WareDTOs.WarePostDto;
import com.example.bsc.repo.WareRepo;

import jakarta.transaction.Transactional;

@Service
public class WareService {

    @Autowired
    private WareRepo wareRepo;

    /**
     *
     * @return alle vorhandene Waren aus der Datenbank als JSON
     */
    public List<WareGetDto> getAll() {
        List<Ware> waren = wareRepo.findAll();
        List<WareGetDto> warenDto = new ArrayList<>();

        if (!waren.isEmpty()) {
            for (Ware ware : waren) {
                WareGetDto dto = new WareGetDto();

                dto.setId(ware.getId());
                dto.setName(ware.getName());
                dto.setMenge(ware.getMenge());

                if (ware.getBestellung() != null) {
                    dto.setBestellunsgnummer(ware.getBestellung().getBestellungsNummer());
                }

                if (ware.getLagerstand() != null) {
                    dto.setLagerstand(ware.getLagerstand());
                }
                warenDto.add(dto);
            }
            return warenDto;
        }
        return warenDto;

    }

    /**
     *
     * @param id von Ware
     * @return gibt die Ware zurück sonst eine Meldung (Status 500)
     */
    public WareGetDto getWareWithID(Long id) {
        Ware ware = wareRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ware nicht gefunden: " + id));

        WareGetDto dto = new WareGetDto();
        dto.setId(ware.getId());
        dto.setName(ware.getName());
        if (ware.getLagerstand() != null) {
            dto.setLagerstand(ware.getLagerstand());
        }
        if (ware.getBestellung() != null) {
            dto.setBestellunsgnummer(ware.getBestellung().getBestellungsNummer());
        }
        return dto;
    }

    @Transactional
    public void addWare(WarePostDto ware) {
        Ware new_ware = new Ware();
        new_ware.setName(ware.getName());
        new_ware.setMenge(ware.getMenge());
        wareRepo.save(new_ware);

    }

    @Transactional
    public void delete(Long id) {
        wareRepo.findById(id).orElseThrow(() -> new RuntimeException("Ware nicht gefunden!"));
        wareRepo.deleteById(id);
    }

}
