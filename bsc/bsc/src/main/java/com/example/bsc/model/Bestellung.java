package com.example.bsc.model;

import java.util.Date;
import java.util.List;

import com.example.bsc.personal.Besteller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="BESTELLUNG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bestellung {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="bestellungsnummer", nullable=false)
    private Long bestellungsNummer;

    @ManyToOne
    @JoinColumn(name="besteller_id", nullable=false)
    private Besteller besteller;

    @Column(name="lieferung", nullable=true)
    private Date lieferDatum;

    @OneToMany(mappedBy="bestellung")
    private List<Ware> warenListe;

}
