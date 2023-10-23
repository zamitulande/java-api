package com.rest.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.enumerations.EnumTestimony;
import com.rest.api.errors.ExceptionNotFound;
import com.rest.api.models.Testimony;
import com.rest.api.services.testimony.TestimonyService;

import jakarta.validation.Valid;

@RestController
public class TestimonyController {

    @Autowired
    TestimonyService testimonyService;

    @GetMapping("/findWithJPQL/{titulo}")
    List<Testimony> findWithJPQL(@PathVariable String titulo) {
        return testimonyService.findWithJPQL(titulo);
    }

    @GetMapping("/DescrpcionCorta/{descripcion_corta}")
    Optional<Testimony> findByDescripcionCorta(@PathVariable String descripcion_corta){
        return testimonyService.findByDescripcionCorta(descripcion_corta);
    }

    @GetMapping("/tipo/{tipo}")
    List<Testimony> findByTipo(@PathVariable EnumTestimony.Tipo tipo){
        return testimonyService.findByTipo(tipo);
    }
    
    @GetMapping("/findTestimonyById/{id}")
    Testimony findTestimonyById(@PathVariable Integer id) throws ExceptionNotFound{
        return testimonyService.findTestimonyById(id);
    }

    @GetMapping("/findAll")
    public List<Testimony> findAllTestimonies(){
        return testimonyService.findAllTestimonies();
    }

    @PostMapping("/created")
    public Testimony saveTestimony(@Valid @RequestBody Testimony testimony){
        return testimonyService.saveTestimony(testimony);
    }

    @PutMapping("/update/{id}")
    public Testimony updateTestimony(@PathVariable Integer id, @RequestBody Testimony testimony){
       return testimonyService.updateTestimony(testimony, id);
    }

    @DeleteMapping("delete/{id}")
    public String deteteTestimony(@PathVariable Integer id){
       testimonyService.deleteTestimony(id);
       return "succesfully delete";   
    }
}
