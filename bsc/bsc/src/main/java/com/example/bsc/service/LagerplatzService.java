package com.example.bsc.service;

import com.example.bsc.einlagerung.lager.Lagerplatz;
import com.example.bsc.repo.LagerplatzRepo;
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
