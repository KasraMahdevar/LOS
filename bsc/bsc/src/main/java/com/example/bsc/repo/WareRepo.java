package com.example.bsc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bsc.model.Ware;

@Repository
public interface WareRepo extends JpaRepository<Ware, Long> {


}
