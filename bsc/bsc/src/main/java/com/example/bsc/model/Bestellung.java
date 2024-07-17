package com.example.bsc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.bsc.personal.Besteller;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name="liefernummer", nullable=false)
    private Long liefernummer;

    @ManyToOne
    @JoinColumn(name="besteller_id", nullable=false)
    private Besteller besteller;

    @Column(name="lieferung", nullable=false)
    private Date lieferDatum;

    @OneToMany(mappedBy="bestellung")
    @JsonIgnore
    private List<Ware> warenListe = new ArrayList<>();



    public int warenAnzahl(){
        return this.warenListe.size();
    }

    public void addWare(Ware ware){
        this.getWarenListe().add(ware);
    }

}
