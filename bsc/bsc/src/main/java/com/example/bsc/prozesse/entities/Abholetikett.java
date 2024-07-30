package com.example.bsc.prozesse.entities;

import com.example.bsc.prozesse.eingangsetikett.Status;
import com.example.bsc.prozesse.lagerplatz.Lager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ABHOLETIKETT_DB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Abholetikett {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "etk_id", nullable = false)
    private Long etk_id;

    @Column(name = "lagerplatz_nr")
    private Long lagerplatznummer;

    @OneToMany
    @JoinColumn(name = "waren_id")
    private List<Ware> warenList = new ArrayList<>();

    @Column(name = "status")
    private Status status = Status.OPEN;

    @ManyToOne
    @JoinColumn(name = "stapler_id")
    private Stapler stapler;

    /**
     * @return eine Lagerplatznummer zur Einlagerung finden,
     * sonst keinen Platz vorhanden Error
     */
    public Long lagerplatz_finden() {
        for (Lagerplatz lagerplatz : Lager.getInstance().getLagerplatzs()) {
            if (!lagerplatz.isBesetzt()) {
                lagerplatz.setBesetzt(true);
                return lagerplatz.getPlatznummer();
            }
        }

        throw new RuntimeException("Alle Lagerplätze sind besetzt!");

    }

}
