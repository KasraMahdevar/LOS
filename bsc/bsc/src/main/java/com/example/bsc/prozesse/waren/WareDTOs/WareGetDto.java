package com.example.bsc.prozesse.waren.WareDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WareGetDto {

    private Long id;
    private String name;
    private Long menge;
    private Long lagerstand = null;
    private Long liefernummer = null;

}
