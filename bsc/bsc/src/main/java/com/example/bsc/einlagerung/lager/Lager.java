package com.example.bsc.einlagerung.lager;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Lager {

    private static Lager instance;
    private List<Lagerplatz> lagerplatzs = new ArrayList<>();

    // verhindert mehrere Objekte direkt von Lager zu erstellen
    private Lager() {
    }

    public static Lager getInstance() {
        if (instance == null) {
            instance = new Lager();
        }
        return instance;
    }

}
