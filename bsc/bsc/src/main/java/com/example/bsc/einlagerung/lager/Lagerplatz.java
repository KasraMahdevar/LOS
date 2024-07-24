package com.example.bsc.einlagerung.lager;

import com.example.bsc.model.Ware;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LAGERPLATZ_DB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Lagerplatz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "platznummer", nullable = false)
    private Long platznummer;

    @Column(name = "bestzt", nullable = false)
    private boolean besetzt = false;

    @OneToMany(mappedBy = "lagerplatz")
    @JsonIgnore
    private List<Ware> warenListe = new ArrayList<>();

    @Column(name = "hight")
    private int hight;

    @Column(name = "width")
    private int width;

    public Lagerplatz(Long platznummer, int hight, int width) {
        this.platznummer = platznummer;
        this.hight = hight;
        this.width = width;
    }
}
