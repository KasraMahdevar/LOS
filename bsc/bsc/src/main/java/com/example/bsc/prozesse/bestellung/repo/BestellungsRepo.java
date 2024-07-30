package com.example.bsc.prozesse.bestellung.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bsc.prozesse.entities.Bestellung;

@Repository
public interface BestellungsRepo extends JpaRepository<Bestellung, Long> {

    Bestellung findBestellungByLiefernummer(Long liefernummer);
}
