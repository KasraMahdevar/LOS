package com.example.bsc.model.BestellungDTOs;


import com.example.bsc.model.Ware;
import com.example.bsc.personal.Besteller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BestellungPostDto {

    private long liefernummer;
    private Date lieferDatum;
    private List<Ware> warenListe;
}
