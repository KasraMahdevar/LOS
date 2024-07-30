package com.example.bsc.prozesse.waren.service;

import java.util.ArrayList;
import java.util.List;

import com.example.bsc.prozesse.entities.Ware;
import com.example.bsc.prozesse.waren.WareDTOs.WareGetDto;
import com.example.bsc.prozesse.waren.WareDTOs.WarePostDto;
import com.example.bsc.prozesse.waren.repo.WareRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Getter
@Setter
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

        if (waren.isEmpty()) {
            throw new RuntimeException("Keine Ware vorhanden!");
        }
        for (Ware ware : waren) {
            WareGetDto dto = new WareGetDto();

            dto.setId(ware.getId());
            dto.setName(ware.getName());
            dto.setMenge(ware.getMenge());

            if (ware.getBestellung() != null) {
                dto.setLiefernummer(ware.getBestellung().getLiefernummer());
            }

            if (ware.getLagerstand() != null) {
                dto.setLagerstand(ware.getLagerstand());
            }
            warenDto.add(dto);
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
            dto.setLiefernummer(ware.getBestellung().getLiefernummer());
        }
        return dto;
    }

    @Transactional
    public Ware addWare(WarePostDto ware) {

        Ware new_ware = new Ware();
        new_ware.setName(ware.getName());
        new_ware.setMenge(ware.getMenge());
        wareRepo.save(new_ware);
        return new_ware;

    }

    @Transactional
    public void delete(Long id) {
        wareRepo.findById(id).orElseThrow(() -> new RuntimeException("Ware nicht gefunden!"));
        wareRepo.deleteById(id);
    }

}
