package com.example.bsc.prozesse.eingangsetikett.service;

import com.example.bsc.prozesse.entities.Abholetikett;
import com.example.bsc.prozesse.eingangsetikett.etikettenDTOs.AbholetikettGetDto;
import com.example.bsc.prozesse.eingangsetikett.Status;
import com.example.bsc.prozesse.eingangsetikett.repo.AbholetikettRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class AbholetikettService {

    @Autowired
    private AbholetikettRepo abholetikettRepo;


    /**
     * @return eine Liste von abholbereite Bestellungen zurück
     */
    public List<AbholetikettGetDto> abholbereite_waren() {
        List<Abholetikett> abholetikettList = abholetikettRepo.findAll().stream().filter(
                etk -> etk.getStatus() == Status.OPEN
        ).collect(Collectors.toList());
        List<AbholetikettGetDto> dtos = new ArrayList<>();
        if (abholetikettList.isEmpty()) {
            throw new RuntimeException("Keine Waren zum Abholen Vorhanden!");
        }
        for (Abholetikett etk : abholetikettList) {
            AbholetikettGetDto abholetikettGetDto = new AbholetikettGetDto();

            abholetikettGetDto.setEtk_nummer(etk.getEtk_id());
            abholetikettGetDto.setLagerplatzNummer(etk.getLagerplatznummer());
            abholetikettGetDto.setStatus(etk.getStatus());
            abholetikettGetDto.setWareList(etk.getWarenList());

            dtos.add(abholetikettGetDto);

        }
        return dtos;
    }

}
