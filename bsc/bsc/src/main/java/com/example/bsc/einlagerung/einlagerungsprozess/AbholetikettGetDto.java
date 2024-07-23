package com.example.bsc.einlagerung.einlagerungsprozess;

import com.example.bsc.model.Ware;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AbholetikettGetDto {

    private Long etk_nummer;
    private Long LagerplatzNummer;
    private List<Ware> wareList;
    private Status status;
}
