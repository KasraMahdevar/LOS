package com.example.bsc.model.BestellungDTOs;


import java.util.Date;
import java.util.List;

import com.example.bsc.model.WareDTOs.WarePostDto;

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
public class BestellungPostDto {

    private long liefernummer;
    private Date lieferDatum;
    private Long besteller_id;
    private List<WarePostDto> warenListe;
}
