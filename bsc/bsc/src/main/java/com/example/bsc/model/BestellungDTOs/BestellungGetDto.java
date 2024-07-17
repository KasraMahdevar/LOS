package com.example.bsc.model.BestellungDTOs;


import java.util.Date;
import java.util.List;

import com.example.bsc.model.Ware;
import com.example.bsc.personal.Besteller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BestellungGetDto {

    private long liefernummer;
    private Besteller besteller;
    private Date lieferDatum;
    private List<Ware> warenListe;

}
