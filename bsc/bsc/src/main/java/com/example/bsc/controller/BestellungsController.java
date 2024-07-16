package com.example.bsc.controller;

import com.example.bsc.model.Bestellung;
import com.example.bsc.model.BestellungDTOs.BestellungGetDto;
import com.example.bsc.service.BestellungsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Tag(name="Eine Bestellung mit ID")
    @GetMapping("/{id}")
    public Bestellung getWithId(@PathVariable Long id){
        return bestellungsService.findBestellungWithId(id);
    }


}
