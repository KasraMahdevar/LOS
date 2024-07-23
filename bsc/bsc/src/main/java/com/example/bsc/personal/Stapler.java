package com.example.bsc.personal;

import com.example.bsc.einlagerung.einlagerungsprozess.Abholetikett;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Collections;
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
    private List<Abholetikett> abholetiketts = Collections.emptyList();

}
