package com.example.bsc.prozesse.stapler.repo;

import com.example.bsc.prozesse.entities.Stapler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaplerRepo extends JpaRepository<Stapler, Long> {
}
