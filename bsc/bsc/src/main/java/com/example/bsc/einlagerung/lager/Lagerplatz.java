package com.example.bsc.einlagerung.lager;

import com.example.bsc.model.Ware;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Lagerplatz {

    //TODO Entity solle hier eingefügt werden

    private Long platznummer;
    private boolean besetzt = false;
    private List<Ware> warenListe = new ArrayList<>();
    private int hight;
    private int width;

    public Lagerplatz(Long platznummer, int hight, int width) {
        this.platznummer = platznummer;
        this.hight = hight;
        this.width = width;
    }
}
