package com.example.bsc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bsc.personal.Besteller;

@Repository
public interface BestellerRepo extends JpaRepository<Besteller, Long>{

}
