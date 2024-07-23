package com.example.bsc.repo;

import com.example.bsc.einlagerung.lager.Lagerplatz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LagerplatzRepo extends JpaRepository<Lagerplatz, Long> {

    Lagerplatz findLagerplatzByPlatznummer(Long platznummer);
}
