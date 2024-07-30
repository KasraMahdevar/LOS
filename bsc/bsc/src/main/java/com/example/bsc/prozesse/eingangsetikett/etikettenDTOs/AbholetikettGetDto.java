package com.example.bsc.prozesse.eingangsetikett.etikettenDTOs;

import com.example.bsc.prozesse.eingangsetikett.Status;
import com.example.bsc.prozesse.entities.Ware;
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
