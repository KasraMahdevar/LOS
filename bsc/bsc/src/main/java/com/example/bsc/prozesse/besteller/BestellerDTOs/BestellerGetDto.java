package com.example.bsc.prozesse.besteller.BestellerDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BestellerGetDto {

    private Long id;
    private String vorname;
    private String nachname;

}
