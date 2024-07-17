package com.example.bsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bsc.personal.BestellerDTOs.BestellerGetDto;
import com.example.bsc.personal.BestellerDTOs.BestellerPostDto;
import com.example.bsc.service.BestellerService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/besteller")
public class BestellerController {

    @Autowired
    private BestellerService bestellerService;


    @Tag(name="Alle Besteller nehmen")
    @GetMapping("/get_all")
    public List<BestellerGetDto> getAll(){
        return bestellerService.getAll();
    }

    @Tag(name="Besteller mit der bestimmten ID nehmen")
    @GetMapping("/{id}")
    public BestellerGetDto getWithId(@Parameter(required=true) @PathVariable Long id){
        return bestellerService.getBestellerWithId(id);
    }

    @Tag(name="Ein Besteller einfügen")
    @PostMapping("/add_besteller")
    public void addBesteller(@Parameter(required=true) @RequestBody BestellerPostDto bestellerPostDto){
        bestellerService.addBesteller(bestellerPostDto);
    }

}
