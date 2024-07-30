package com.example.bsc.prozesse.besteller.repo;

import com.example.bsc.prozesse.entities.Besteller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestellerRepo extends JpaRepository<Besteller, Long>{

}
