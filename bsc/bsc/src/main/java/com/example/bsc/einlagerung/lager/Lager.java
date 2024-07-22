package com.example.bsc.einlagerung.lager;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Lager {

    private Long lagerplatzNummer;
    private Long höhe;
    private Long breite;
    private boolean besetzt;
}
