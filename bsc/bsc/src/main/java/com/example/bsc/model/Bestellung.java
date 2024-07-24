package com.example.bsc.model;

import com.example.bsc.personal.Besteller;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BESTELLUNG", indexes = {
        @Index(name = "idx_bestellung_freigabe", columnList = "freigabe")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bestellung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "liefernummer", nullable = false)
    private Long liefernummer;

    @ManyToOne
    @JoinColumn(name = "besteller_id", nullable = false)
    private Besteller besteller;

    @Column(name = "lieferung", nullable = false)
    private Date lieferDatum;

    @OneToMany(mappedBy = "bestellung")
    @JsonIgnore
    private List<Ware> warenListe = new ArrayList<>();

    @Column(name = "freigabe")
    private boolean freigabe = false;


    public boolean getFreigabe() {
        return freigabe;
    }

    public void setFreigabe(boolean freigabe) {
        this.freigabe = freigabe;
    }
}
