package com.rest.api.services.testimony;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.api.enumerations.EnumTestimony;
import com.rest.api.errors.ExceptionNotFound;
import com.rest.api.models.Testimony;
import com.rest.api.repositories.testimony.TestimonyRepository;

@Service
public class TestimonyServiceImplem implements TestimonyService {

    @Autowired
    TestimonyRepository testimonyRepository;

    @Override
    public List<Testimony> findAllTestimonies() {
        return testimonyRepository.findAll();
    }


    @Override
    @Transactional
    public Testimony saveTestimony(Testimony testimony) {
        return testimonyRepository.save(testimony);
    }

    @Override
    @Transactional
    public Testimony updateTestimony(Testimony testimony, Integer id) {        

       Testimony optionTestimony = testimonyRepository.findById(id).get();

        if (Objects.nonNull(testimony.getTitulo()) && !"".equalsIgnoreCase(testimony.getTitulo())){
            optionTestimony.setTitulo(testimony.getTitulo());
        }        
        if (Objects.nonNull(testimony.getDescripcion_corta()) && !"".equalsIgnoreCase(testimony.getDescripcion_corta())){
            optionTestimony.setDescripcion_corta((testimony.getDescripcion_corta()));
        }
        if (Objects.nonNull(testimony.getTipo()) && !"".equalsIgnoreCase(testimony.getTipo().toString())){
            optionTestimony.setTipo(testimony.getTipo());
        }
        return testimonyRepository.save(optionTestimony);


    }

    @Override
    public void deleteTestimony(Integer id) {
        testimonyRepository.deleteById(id);
    }


    @Override
    public List<Testimony> findWithJPQL(String titulo) {
        return testimonyRepository.findWithJPQL(titulo);
    }

    @Override
    public List<Testimony> findByTipo(EnumTestimony.Tipo tipo){
        return testimonyRepository.findByTipo(tipo);
    }

    @Override
    public Optional<Testimony> findByDescripcionCorta(String descripcion_corta){
        return testimonyRepository.findByDescripcionCorta(descripcion_corta);
    }

    @Override
    public Testimony findTestimonyById(Integer id) throws ExceptionNotFound {
        Optional<Testimony> testimonio = testimonyRepository.findById(id);
        if(!testimonio.isPresent()){
            throw new ExceptionNotFound("Testimonio is not avaliable");
        }
       return testimonio.get();
    }

}
