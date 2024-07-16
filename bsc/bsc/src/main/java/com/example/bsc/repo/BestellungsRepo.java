package com.example.bsc.repo;

import com.example.bsc.model.Bestellung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestellungsRepo extends JpaRepository<Bestellung, Long> {
}
