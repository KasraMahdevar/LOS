package com.example.bsc.prozesse.lagerplatz.service;

import com.example.bsc.prozesse.entities.Lagerplatz;
import com.example.bsc.prozesse.lagerplatz.repo.LagerplatzRepo;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class LagerplatzService {

    @Autowired
    private LagerplatzRepo lagerplatzRepo;

    @Transactional
    public void save_lagerplatz_in_db(Lagerplatz lagerplatz){
        lagerplatzRepo.save(lagerplatz);
    }
}
