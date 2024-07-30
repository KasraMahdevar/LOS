package com.example.bsc.prozesse.waren.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bsc.prozesse.entities.Ware;

@Repository
public interface WareRepo extends JpaRepository<Ware, Long> {


}
