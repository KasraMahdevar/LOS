package com.example.bsc.repo;

import com.example.bsc.einlagerung.einlagerungsprozess.Abholetikett;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AbholetikettRepo extends JpaRepository<Abholetikett, Long> {

    Abholetikett findAbholetikettByEtk_nummer(Long etk_nummer);
}
