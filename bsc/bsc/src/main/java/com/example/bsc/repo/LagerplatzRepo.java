package com.example.bsc.repo;

import com.example.bsc.einlagerung.lager.Lagerplatz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LagerplatzRepo extends JpaRepository<Lagerplatz, Long> {

    @Query("SELECT l FROM Lagerplatz l WHERE l.platznummer=:platznummer")
    Lagerplatz findLagerplatzByPlatznummer(@Param("platznummer") Long platznummer);
}
