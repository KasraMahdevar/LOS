package com.example.bsc.prozesse.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity()
@Table(name = "BESTELLER_DB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Besteller extends Personal {


    @OneToMany(mappedBy = "besteller")
    @Nullable
    @JsonIgnore
    private List<Bestellung> bestellungen;

    public int anzahlBestllungen() {
        if (bestellungen == null) {
            return 0;
        } else {
            return bestellungen.size();
        }
    }

}
