package com.example.bsc.prozesse.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
