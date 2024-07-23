package com.example.bsc.repo;

import com.example.bsc.personal.Stapler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaplerRepo extends JpaRepository<Stapler, Long> {
}
