package com.example.bsc.einlagerung.einlagerungsprozess;

import com.example.bsc.einlagerung.lager.Lager;
import com.example.bsc.einlagerung.lager.Lagerplatz;
import com.example.bsc.model.Ware;
import com.example.bsc.personal.Stapler;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "ABHL_ETK")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Abholetikett {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "etk_nummer", nullable = false)
    private Long etk_nummer;

    @Column(name = "lagerplatz_nr")
    private Long lagerplatznummer;

    @OneToMany
    @JoinColumn(name = "waren_id")
    private List<Ware> warenList = Collections.emptyList();

    @Column(name = "status")
    private Status status = Status.OPEN;

    @ManyToOne
    @JoinColumn(name = "stapler_id")
    private Stapler stapler;


    public Abholetikett(List<Ware> warenList) {
        this.warenList = warenList;
        this.lagerplatznummer = lagerplatz_finden();
    }

    /**
     * @return eine Lagerplatznummer zur Einlagerung finden,
     *         sonst keinen Platz vorhanden Error
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
