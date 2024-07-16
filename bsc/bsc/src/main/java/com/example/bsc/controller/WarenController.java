package com.example.bsc.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bsc.model.WareDTOs.WareGetDto;
import com.example.bsc.model.WareDTOs.WarePostDto;
import com.example.bsc.service.WareService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/v1/waren")
public class WarenController {

    @Autowired
    private WareService wareService;

    @Tag(name = "Alle Waren")
    @GetMapping("/get_all")
    public List<WareGetDto> getAll() {
        return wareService.getAll();

    }

    @Tag(name="Eine bestimmte Ware durch die ID finden")
    @GetMapping("/{id}")
    public WareGetDto getWareWithID(@Parameter(required=true) @PathVariable Long id){
        return wareService.getWareWithID(id);
    }

    @Tag(name = "Neue Ware hinzufügen")
    @PostMapping("/add_ware")
    public void addWare(@Parameter(required = true) @RequestBody WarePostDto ware_dto) {
        wareService.addWare(ware_dto);
    }

    @Tag(name="Löschen eine Ware durch ID")
    @DeleteMapping("/delete/{id}")
    public void deleteWare(@PathVariable Long id){
        wareService.delete(id);
    }


}
