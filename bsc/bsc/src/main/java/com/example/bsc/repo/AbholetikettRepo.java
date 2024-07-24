package com.example.bsc.repo;

import com.example.bsc.einlagerung.einlagerungsprozess.Abholetikett;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AbholetikettRepo extends JpaRepository<Abholetikett, Long> {


    @Query("SELECT a FROM Abholetikett a WHERE a.etk_id=:etk_id")
    Abholetikett findAbholetikettByEtk_nummer(@Param("etk_id") Long etk_id);
}
