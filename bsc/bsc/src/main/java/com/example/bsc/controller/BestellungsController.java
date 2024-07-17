package com.example.bsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bsc.model.Bestellung;
import com.example.bsc.model.BestellungDTOs.BestellungGetDto;
import com.example.bsc.model.BestellungDTOs.BestellungPostDto;
import com.example.bsc.service.BestellungsService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/v1/bestellungen")
public class BestellungsController {

    @Autowired
    private BestellungsService bestellungsService;

    @Tag(name="Alle Bestellungen")
    @GetMapping("/get_all")
    public List<BestellungGetDto> getAll(){
        return bestellungsService.getAll();
    }


    @Tag(name="Eine neue Bestellung einfügen")
    @PostMapping("/add_bestellung")
    public void addBestellung(@Parameter(required=true) @RequestBody BestellungPostDto bestellungPostDto){
        bestellungsService.addBestellung(bestellungPostDto);
    }

    @Tag(name="Eine Bestellung mit ID nehmen")
    @GetMapping("/{id}")
    public Bestellung getWithId(@Parameter(required=true) @PathVariable Long id){
        return bestellungsService.findBestellungWithId(id);
    }


}
