package com.example.bsc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "WARE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Ware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "menge", nullable = false)
    private Long menge;

    @Column(name = "lagerstand", nullable = true)
    @JsonIgnore
    private Long lagerstand;

    @ManyToOne
    @JoinColumn(name = "bestellung_id")
    @Nullable
    @JsonIgnore
    private Bestellung bestellung;

}
