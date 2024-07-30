package com.example.bsc.prozesse.entities;

import com.example.bsc.prozesse.entities.Abholetikett;
import com.example.bsc.prozesse.entities.Personal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STAPLER_DB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Stapler extends Personal {

    @OneToMany(mappedBy = "stapler")
    @Column(name = "ABHL_ETK")
    private List<Abholetikett> abholetiketts = new ArrayList<>();

}
